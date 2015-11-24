package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import scotlandyard.*;

public class ScotlandYardController implements Spectator {

	static final private String graphFileName = "resources/graph.txt";
	static final private String posFileName = "/solution/resources/pos.txt";
	static final private String buttonSoundFileName = "resources/buttonSound.wav";
	static final private Boolean[] rounds = { true, true, true, true, true, true, true, true, true, true,
		true, true, true, true, true, true, true, true, true, true, true, true, true };
	static final private int MRX_TAXI_TICKETS = 57;
	static final private int MRX_BUS_TICKETS = 45;
	static final private int MRX_UNDERGROUND_TICKETS = 23;
	static final private int MRX_SECRETMOVE_TICKETS = 5;
	static final private int MRX_DOUBLEMOVE_TICKETS = 2;
	static final private Map<Ticket, Integer> MrXTickets = new HashMap<Ticket, Integer>();
	static private int[] DETECTIVE_LOCATIONS = { 13, 26, 29, 34, 50, 53, 91, 94, 103, 112, 117, 123, 138, 141, 155, 174 };
	static private int[] MRX_LOCATIONS = { 35, 45, 51, 71, 78, 104, 106, 127, 132, 146, 166, 170, 172 };

	/**
	 * 
	 * The game can have two states
	 *
	 */
	enum STATE {
		MAIN, GAME
	}

	BlockingQueue<List<Move>> validMoves;
	BlockingQueue<Move> selectedMoves;
	private List<Move> lastValid; // Last received list of valid moves
	private Timer pollTimer; // Timer that polls for valid moves

	private ScotlandYardMainContainer mainView;
	private ScotlandYardModel gameModel;

	private boolean showRealLocation;

	private HashMap<String, List<Integer>> coordinateMap;
	private STATE viewState;
	private Animation animation;
	private boolean unsavedFlag;

	Thread thread;
	Thread animationThread;

	/**
	 * Constructor for the Scotland Yard controller
	 * 
	 * @param mainView
	 *            Reference to the main frame
	 * @param gameModel
	 *            Reference to the game model
	 * @param viewState
	 *            State of the game
	 */
	public ScotlandYardController(ScotlandYardMainContainer mainView, ScotlandYardModel gameModel, STATE viewState) {
		// Initialise blocking queues
		validMoves = new LinkedBlockingQueue<List<Move>>();
		selectedMoves = new LinkedBlockingQueue<Move>();

		Scanner in = null;
		in = new Scanner(ScotlandYardController.class.getClass().getResourceAsStream(posFileName));
		coordinateMap = new HashMap<String, List<Integer>>();
		// get the number of nodes
		String topLine = in.nextLine();
		int numberOfNodes = Integer.parseInt(topLine);
		for (int i = 0; i < numberOfNodes; i++) {
			String line = in.nextLine();

			String[] parts = line.split(" ");
			List<Integer> pos = new ArrayList<Integer>();
			pos.add(Integer.parseInt(parts[1]));
			pos.add(Integer.parseInt(parts[2]));

			String key = parts[0];
			coordinateMap.put(key, pos);
		}
		in.close();
		this.mainView = mainView;
		this.gameModel = gameModel;
		this.viewState = viewState;
		renderBasedOnState();
	}

	/**
	 * If the state of the game is <i>MAIN</i>, the main frame will be set up as
	 * the main menu. If it is <i>GAME</i>, the map and listeners of the game
	 * are initialised and the map is shown on screen
	 */
	private void renderBasedOnState() {
		mainView.setVisible(false);
		if (viewState == STATE.MAIN) {
			if (pollTimer != null)
				pollTimer.stop();
			mainView.setUpMainMenu();
			mainView.getMainMenuPanel().addMenuListener(new MainMenuListener());
			mainView.listenerActive = false;
			mainView.getGlassPane().setVisible(false);
		} else if (viewState == STATE.GAME) {
			mainView.setUpGameGUI();
			// Try to fetch new valid moves every 150ms
			pollTimer = new Timer(150, new PollValidMoves());
			pollTimer.start();
			mainView.getGameView().addMapListener(new MapMouseListener());
			mainView.getGameView().addMapButtonsListener(new MapButtonsListener());
			updateTicketsInView();
			updateMrXLastLocationInView();
			updateNames();
			updateRoundCountInView();
			updateRevealButton(true);

			List<Ticket> mrXTicketLog = gameModel.getMrXTicketLog();
			for (int i = 0; i < mrXTicketLog.size(); i++) {
				Ticket ticket = mrXTicketLog.get(i);
				mainView.getGameView().setTravelLogIcon(ticket, i + 1);
			}
			for (Colour c : gameModel.getPlayers())
				updateLocationInView(c);
			animatePlayer(gameModel.getCurrentPlayer());
			checkWin();
		}
		mainView.setVisible(true);
	}

	/**
	 * Creates a new game model based on the input given by the user
	 * 
	 * @param tickets
	 * @param names
	 * @return a game model
	 */
	private ScotlandYardModel createGameModel(Map<Colour, Map<Ticket, Integer>> tickets, Map<Colour, String> names) {
		try {
			unsavedFlag = true;
			showRealLocation = false;
			MrXTickets.put(Ticket.Taxi, MRX_TAXI_TICKETS);
			MrXTickets.put(Ticket.Bus, MRX_BUS_TICKETS);
			MrXTickets.put(Ticket.Underground, MRX_UNDERGROUND_TICKETS);
			MrXTickets.put(Ticket.SecretMove, MRX_SECRETMOVE_TICKETS);
			MrXTickets.put(Ticket.DoubleMove, MRX_DOUBLEMOVE_TICKETS);
			// If graph.txt is not found then an open file dialog is shown and
			// the user is asked to locate the file
			try {
				gameModel = new ScotlandYardModel(tickets.size(), Arrays.asList(rounds), graphFileName);
			} catch (IOException e) {
				mainView.showErrorMessage(mainView, "Missing resources! Please locate graph.txt");
				File file = mainView.showFileChooser(mainView, "Open graph.txt file", false);
				if (file == null)
					throw new IOException();
				String path = file.getAbsolutePath();
				gameModel = new ScotlandYardModel(tickets.size(), Arrays.asList(rounds), path);
			}
			gameModel.spectate(this);

			// Locations are selected randomly from the predefined arrays
			// MrX joins the game
			gameModel.join(new MrX(selectedMoves, validMoves), Colour.Black,
					Utilities.uniqueNumberGenerator(MRX_LOCATIONS), MrXTickets);
			// All the other detectives join
			for (Map.Entry<Colour, Map<Ticket, Integer>> player : tickets.entrySet()) {
				Colour c = player.getKey();
				gameModel.join(new Detective(c, names.get(c), selectedMoves, validMoves), c,
						Utilities.uniqueNumberGenerator(DETECTIVE_LOCATIONS), player.getValue());
			}

			// The game loop starts in a new thread
			thread = new Thread(gameModel);
			thread.setName("Game Loop");
			thread.start();
		} catch (IOException e) {
			mainView.showErrorMessage(mainView, "Missing resources! Please reinstall the game!");
			System.exit(1);
		}
		return gameModel;
	}

	/**
	 * This constructor is used for loading game where the current round,
	 * currentPlayer, mrXLastLocation and ticketLog are also given
	 * 
	 * @param tickets
	 * @param names
	 * @param locations
	 * @param mrXLastLocation
	 * @param round
	 * @param currentPlayer
	 * @param mrXTicketLog
	 * @return a game model representing the loaded save file
	 */
	private ScotlandYardModel createGameModel(Map<Colour, Map<Ticket, Integer>> tickets, Map<Colour, String> names,
			Map<Colour, Integer> locations, int mrXLastLocation, int round, Colour currentPlayer,
			List<Ticket> mrXTicketLog) {
		try {
			unsavedFlag = false;
			showRealLocation = false;
			try {
				gameModel = new ScotlandYardModel(tickets.size() - 1, Arrays.asList(rounds), graphFileName,
						mrXLastLocation, round, mrXTicketLog);
			} catch (IOException e) {
				mainView.showErrorMessage(mainView, "Missing resources! Please locate graph.txt");
				File file = mainView.showFileChooser(mainView, "Open graph.txt file", false);
				if (file == null)
					throw new IOException();
				String path = file.getAbsolutePath();
				gameModel = new ScotlandYardModel(tickets.size() - 1, Arrays.asList(rounds), path, mrXLastLocation,
						round, mrXTicketLog);
			}

			gameModel.spectate(this);
			for (Map.Entry<Colour, Map<Ticket, Integer>> player : tickets.entrySet()) {
				Colour c = player.getKey();
				if (c != Colour.Black)
					gameModel.join(new Detective(c, names.get(c), selectedMoves, validMoves), c, locations.get(c),
							player.getValue());
				else {
					gameModel.join(new MrX(selectedMoves, validMoves), c, locations.get(c), player.getValue());
				}
			}
			gameModel.setCurrentPlayer(currentPlayer);
			// START
			thread = new Thread(gameModel);
			thread.setName("Game Loop");
			thread.start();
		} catch (IOException e) {
			mainView.showErrorMessage(mainView, "Missing resources! Please reinstall the game!");
			System.exit(1);
		}
		return gameModel;
	}

	/**
	 * Method called by MovePopUp to add the selected move to the blocking queue
	 * 
	 * @param move
	 *            Selected move
	 */
	public void addToSelected(Move move) {
		try {
			selectedMoves.put(move);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notify(Move move) {
		if (animation != null)
			animation.stopAnimation();

		if (move instanceof MoveTicket)
			notify((MoveTicket) move);
		else if (move instanceof MoveDouble)
			notify((MoveDouble) move);

		showRealLocation = false;
		updateMrXLastLocationInView();
		updateRevealButton(false);

		updateRoundCountInView();

		mainView.getGameView().repaint();

		animatePlayer(gameModel.getNextPlayer());

		checkWin();

		unsavedFlag = true;
	}

	/**
	 * Updates the ticket number in view. Updates the player location in view.
	 * If MrX, adds move to travel log.
	 * 
	 * @param m
	 *            The player move
	 */
	private void notify(MoveTicket m) {
		updateTicketsInView(m.colour, m.ticket);
		updateLocationInView(m.colour, m.target);
		if (m.colour == Colour.Black)
			mainView.getGameView().setTravelLogIcon(m.ticket, gameModel.getRound());
	}

	/**
	 * Called if a player makes a double move. Updates the ticket number in
	 * view.
	 * 
	 * @param move
	 *            The double move
	 */
	private void notify(MoveDouble move) {
		updateTicketsInView(move.colour, Ticket.DoubleMove);
	}

	/**
	 * Enables the <i>Reveal</i> button if it's MrX's turn
	 * 
	 * @param initialise
	 *            Set to true if called for the first time
	 */
	private void updateRevealButton(boolean initialise) {
		Colour c;
		if (initialise)
			c = gameModel.getCurrentPlayer();
		else
			c = gameModel.getNextPlayer();
		if (c != Colour.Black) {
			mainView.getGameView().disableRevealButton();
			mainView.getGameView().resetButtonText();
		} else
			mainView.getGameView().enableRevealButton();
	}

	/**
	 * Create a new animation instance and runs it on a new thread
	 * 
	 * @param c
	 *            The colour of the player to animate
	 */
	private void animatePlayer(Colour c) {
		animation = new Animation(mainView.getGameView(), c);
		animationThread = new Thread(animation);
		animationThread.setName("Animation Thread");
		animationThread.start();
	}

	/**
	 * Update specific ticket of specific player
	 * 
	 * @param colour
	 * @param ticket
	 */
	private void updateTicketsInView(Colour colour, Ticket ticket) {
		mainView.getGameView().setTicket(colour, ticket, gameModel.getPlayerTickets(colour, ticket));
	}

	/**
	 * Update all tickets of specified player
	 * 
	 * @param colour
	 */
	private void updateTicketsInView(Colour colour) {
		mainView.getGameView().setTicket(colour, gameModel.getPlayerTickets(colour));
	}

	/**
	 * Updates all tickets of all players
	 */
	private void updateTicketsInView() {
		for (Colour colour : gameModel.getPlayers())
			updateTicketsInView(colour);
	}

	/**
	 * Updates MrX's last location
	 */
	private void updateMrXLastLocationInView() {
		if (!showRealLocation)
			mainView.getGameView().setMrXLocation(gameModel.getPlayerLocation(Colour.Black));
		else
			mainView.getGameView().setMrXLocation(gameModel.getMrXRealLocation());
	}

	/**
	 * Update name labels
	 */
	private void updateNames() {
		Map<Colour, String> names = gameModel.getNames();
		for (Map.Entry<Colour, String> entry : names.entrySet()) {
			mainView.getGameView().setName(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Updates the round count in view
	 */
	private void updateRoundCountInView() {
		mainView.getGameView().setRound(gameModel.getRound());
	}

	/**
	 * Updates location of player in view, based on location in the model
	 * 
	 * @param c
	 */
	private void updateLocationInView(Colour c) {
		int playerLoc = gameModel.getPlayerLocation(c);
		if (!gameModel.isMrXVisible() && c == Colour.Black) {
			mainView.getGameView().resetLocationToNull(c);
			return;
		}
		List<Integer> coordinates = coordinateMap.get(playerLoc + "");
		if (coordinates == null) {
			mainView.getGameView().resetLocationToNull(c);
			return;
		}
		mainView.getGameView().updateLocations(c, coordinates.get(0), coordinates.get(1));
	}

	/**
	 * Updates location of player in view, based on the given location
	 * 
	 * @param c
	 * @param playerLoc
	 */
	private void updateLocationInView(Colour c, int playerLoc) {
		if (!gameModel.isMrXVisible() && c == Colour.Black) {
			mainView.getGameView().resetLocationToNull(c);
			return;
		}
		List<Integer> coordinates = coordinateMap.get(playerLoc + "");
		if (coordinates == null) {
			mainView.getGameView().resetLocationToNull(c);
			return;
		}
		mainView.getGameView().updateLocations(c, coordinates.get(0), coordinates.get(1));
	}

	/**
	 * Checks if the game is over and makes the suitable changes in the view
	 * Stops animations, disables input and shows GlassPane
	 */
	private void checkWin() {
		if (gameModel.isGameOver()) {
			mainView.setWinReason(gameModel.getWinReason());
			if (animation != null)
				animation.stopAnimation();
			mainView.showAndRepaintGlass();

			// Show win banner and wait 10 seconds before returning to main menu
			ReturnToMainListener taskPerformer = new ReturnToMainListener();
			Timer timer = new Timer(10000, taskPerformer);
			timer.setRepeats(false);
			timer.start();
		}
	}

	/**
	 * Opens save dialog and saves game
	 */
	private void saveGame() {
		File selectedFile = mainView.showFileChooser(mainView, "Choose where to save game", true);
		if (selectedFile != null) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
				writer.write(gameModel.toString());
				writer.close();
				unsavedFlag = false;
			} catch (IOException e) {
				mainView.showErrorMessage(mainView, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads game from selected file and creates a new game model
	 * 
	 * @param selectedFile
	 *            The game save to load
	 * @throws FileNotFoundException
	 *             When file is not found
	 * @throws ArrayIndexOutOfBoundsException
	 *             When the file is corrupted or wrong
	 */
	private void loadGame(File selectedFile) throws FileNotFoundException, ArrayIndexOutOfBoundsException {

		if (selectedFile != null) {
			Scanner reader = new Scanner(selectedFile);
			String[] topLine = reader.nextLine().split(",");
			int mrXLastLocation = Integer.parseInt(topLine[0]);
			int round = Integer.parseInt(topLine[1]);
			Colour currentPlayer = Colour.valueOf(topLine[2]);
			Map<Colour, Map<Ticket, Integer>> tickets = new LinkedHashMap<Colour, Map<Ticket, Integer>>();
			Map<Colour, String> names = new HashMap<Colour, String>();
			Map<Colour, Integer> locations = new HashMap<Colour, Integer>();
			List<Ticket> mrXTicketLog = new ArrayList<Ticket>();

			String secondLine = reader.nextLine();
			if (!secondLine.isEmpty()) {
				String[] secondLineSplitted = secondLine.split(",");
				for (String ticket : secondLineSplitted)
					mrXTicketLog.add(Ticket.valueOf(ticket));
			}

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				if (line.isEmpty())
					continue;
				String[] lineSplitted = line.split(",");
				Colour c = Colour.valueOf(lineSplitted[0]);
				locations.put(c, Integer.parseInt(lineSplitted[1]));
				Map<Ticket, Integer> playerTickets = new HashMap<Ticket, Integer>();
				playerTickets.put(Ticket.valueOf(lineSplitted[2].split(" ")[0]),
						Integer.parseInt(lineSplitted[2].split(" ")[1]));
				playerTickets.put(Ticket.valueOf(lineSplitted[3].split(" ")[0]),
						Integer.parseInt(lineSplitted[3].split(" ")[1]));
				playerTickets.put(Ticket.valueOf(lineSplitted[4].split(" ")[0]),
						Integer.parseInt(lineSplitted[4].split(" ")[1]));
				playerTickets.put(Ticket.valueOf(lineSplitted[5].split(" ")[0]),
						Integer.parseInt(lineSplitted[5].split(" ")[1]));
				playerTickets.put(Ticket.valueOf(lineSplitted[6].split(" ")[0]),
						Integer.parseInt(lineSplitted[6].split(" ")[1]));
				tickets.put(c, playerTickets);
				if (c != Colour.Black) {
					names.put(c, lineSplitted[7]);
				}
			}
			reader.close();

			gameModel = createGameModel(tickets, names, locations, mrXLastLocation, round, currentPlayer, mrXTicketLog);
		}
	}

	/**
	 * Polls for valid moves and sends their coordinates to view
	 */
	class PollValidMoves implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<Move> last = validMoves.poll();
			if (last != null) {
				lastValid = last;
				if (mainView.getGameView().validCoordinates != null) {
					mainView.getGameView().validCoordinates.clear();
					if (gameModel.getCurrentPlayer() != Colour.Black) {
						for (Move move : lastValid) {
							if (move instanceof MoveTicket) {
								mainView.getGameView().validCoordinates.add(coordinateMap
										.get(((MoveTicket) move).target + ""));
							} else if (move instanceof MoveDouble) {
								MoveTicket m2 = (MoveTicket) (((MoveDouble) move).moves.get(1));
								mainView.getGameView().validCoordinates.add(coordinateMap.get(m2.target + ""));
							}
						}
					}
				}
				mainView.getGameView().repaint();
			}
		}
	}

	/**
	 * Created after a game is won to return to the Main Menu
	 */
	class ReturnToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewState = STATE.MAIN;
			if (gameModel != null) {
				gameModel.stop();
				viewState = STATE.MAIN;
				renderBasedOnState();
			}
		}
	}

	/**
	 * Listener for all the button in the game view
	 */
	class MapButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("hidereveal")) {
				if (showRealLocation)
					showRealLocation = false;
				else
					showRealLocation = true;
				updateMrXLastLocationInView();
				mainView.getGameView().toggleRevealButtonText();
			} else if (arg0.getActionCommand().equals("save")) {
				saveGame();
			} else if (arg0.getActionCommand().equals("back")) {
				// Asks if there is unsaved progress
				if (showDialogIfUnsaved()) {
					if (gameModel != null)
						gameModel.stop();
					if (animation != null)
						animation.stopAnimation();
					viewState = STATE.MAIN;
					renderBasedOnState();

				}
			} else if (arg0.getActionCommand().equals("load")) {
				try {
					if (showDialogIfUnsaved()) {
						File selectedFile = mainView.showFileChooser(mainView, "Choose save file", false);
						if (selectedFile != null) {
							if (gameModel != null && gameModel.isReady())
								gameModel.stop();
							loadGame(selectedFile);
							if (gameModel != null) {
								viewState = STATE.GAME;
								renderBasedOnState();
							}
						}
					}
				} catch (FileNotFoundException e) {
					mainView.showErrorMessage(mainView, "File not found!");
					e.printStackTrace();
				} catch (Exception e) {
					mainView.showErrorMessage(mainView, "Wrong or corrupted file!");
					e.printStackTrace();
				}
			} else if (arg0.getActionCommand().equals("exit")) {
				int dialogResult = mainView.showConfirmDialog(mainView, "Are you sure you want to exit?",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION)
					System.exit(0);
			} else if (arg0.getActionCommand().equals("mute")) {
				mainView.toggleSong();
			}
		}

		/**
		 * @return True if game is saved or the user has pressed yes on the
		 *         dialog. False otherwise
		 */
		private boolean showDialogIfUnsaved() {
			boolean save = true;
			if (unsavedFlag) {
				if (mainView.showConfirmDialog(mainView,
						"You have unsaved progress. Are you sure you want to continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
					save = false;
			}
			return save;
		}
	}

	/**
	 * 
	 * Listener for the main menu buttons
	 */
	class MainMenuListener implements ActionListener {

		URL buttonSoundURL = this.getClass().getResource(buttonSoundFileName);

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Utilities.SoundUtilities.playSound(buttonSoundURL);
			String action = arg0.getActionCommand();
			if (action.equals("play")) {
				mainView.showNewGameDialog(mainView, true);
				if (mainView.getDialogObject().isSet()) {
					Map<Colour, Map<Ticket, Integer>> tickets = mainView.getDialogObject().getTickets();
					Map<Colour, String> names = mainView.getDialogObject().getNames();
					gameModel = createGameModel(tickets, names);
					viewState = STATE.GAME;
					renderBasedOnState();
				}
			}
			if (action.equals("load")) {
				try {
					File selectedFile = mainView.showFileChooser(mainView, "Open game save", false);
					if (selectedFile != null) {
						loadGame(selectedFile);
						if (gameModel != null) {
							viewState = STATE.GAME;
							renderBasedOnState();
						}
					}
				} catch (FileNotFoundException e) {
					mainView.showErrorMessage(mainView, "File not found!");
					e.printStackTrace();
				} catch (Exception e) {
					mainView.showErrorMessage(mainView, "Wrong or corrupted file!");
					e.printStackTrace();
				}
			}
			if (action.equals("exit"))
				System.exit(0);
		}
	}

	/**
	 * Listener for all map functions Checks whether the mouse is located on a
	 * number or if it clicks a valid number
	 */
	class MapMouseListener extends MouseAdapter {

		@Override
		public void mouseMoved(MouseEvent arg0) {
			int numberOver = findNumber(arg0);
			List<Integer> mouseLocation = coordinateMap.get("" + numberOver);
			mainView.getGameView().mouseLocation = mouseLocation;
			mainView.getGameView().repaint();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			showPopup(arg0);
		}

		private void showPopup(MouseEvent arg0) {
			int numberClicked = findNumber(arg0);
			List<Move> movesForClick = new ArrayList<Move>();
			if (numberClicked != 0) {
				for (Move move : lastValid) {
					if (move instanceof MoveTicket) {
						MoveTicket mticket = (MoveTicket) move;
						if (mticket.target == numberClicked) {
							movesForClick.add(mticket);
						}
					} else if (move instanceof MoveDouble) {
						MoveTicket mt2 = (MoveTicket) ((MoveDouble) move).moves.get(1);
						if (mt2.target == numberClicked) {
							movesForClick.add(move);
						}
					}
				}
				if (movesForClick.size() != 0) {
					mainView.getGameView().showPopupMenu(movesForClick, ScotlandYardController.this, arg0.getX(),
							arg0.getY());
				}
			}
		}

		/**
		 * Find if the mouse is over a number
		 * 
		 * @param arg0
		 *            The mouse event
		 * @return 0 if the mouse is not on a number. The number otherwise
		 */
		private int findNumber(MouseEvent arg0) {
			int numberClicked = 0;
			int X = arg0.getX();
			int Y = arg0.getY();
			for (Map.Entry<String, List<Integer>> coordinate : coordinateMap.entrySet()) {
				int x1 = coordinate.getValue().get(0) + 5;
				int y1 = coordinate.getValue().get(1) + 5;
				// (X-x1)^2+(Y-y1)^2 = r^2
				int dSquared = (X - x1) * (X - x1) + (Y - y1) * (Y - y1);
				if (dSquared <= 100) {
					numberClicked = Integer.parseInt(coordinate.getKey());
					break;
				}
			}
			return numberClicked;
		}

	}

}
