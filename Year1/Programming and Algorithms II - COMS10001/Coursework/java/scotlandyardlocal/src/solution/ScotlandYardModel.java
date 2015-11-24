package solution;

import scotlandyard.*;

import java.io.IOException;
import java.util.*;

/**
 * A representation of a Scotland Yard game. Holds all the required data
 * @author Andreas Georgiou(ag14774)
 * @author Constantinos Theophilou(ct14872)
 */
public class ScotlandYardModel extends ScotlandYard implements Runnable {

	private boolean isReady;
	private int numberOfDetectives;
	private int mrXLastLocation;
	private int round;
	private Colour currentPlayer;
	private List<Boolean> rounds;
	private List<Ticket> mrXTicketLog;
	private Map<Colour, Colour> nextPlayers;
	private Map<Colour, Player> players;
	private Map<Colour, Integer> locations;
	private Map<Colour, Map<Ticket, Integer>> tickets;
	private WinReason winReason;

	private List<Spectator> spectators;

	private Graph<Integer, Route> graph;

	/**
	 * The constructor for a Scotland Yard game.
	 * 
	 * @param numberOfDetectives
	 *            The number of detectives needed to start the game
	 * @param rounds
	 * @param graphFileName
	 * @throws IOException
	 *             Exception in case <i>graphFileName</i> is not found.
	 */
	public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName) throws IOException {
		super(numberOfDetectives, rounds, graphFileName);
		if (numberOfDetectives >= 0)
			this.numberOfDetectives = numberOfDetectives;
		else
			throw new Error("The number of detectives cannot be negative");
		this.rounds = rounds;
		ScotlandYardGraphReader reader = new ScotlandYardGraphReader();
		try {
			graph = reader.readGraph(graphFileName);
		} catch (IOException e) {
			graph = reader.readGraph("graph.txt");
		}

		// Initialise
		winReason = WinReason.None;
		isReady = false;
		round = 0;
		mrXLastLocation = 0;
		currentPlayer = Colour.Black;
		mrXTicketLog = new ArrayList<Ticket>();
		players = new LinkedHashMap<Colour, Player>();
		locations = new HashMap<Colour, Integer>();
		tickets = new HashMap<Colour, Map<Ticket, Integer>>();
		nextPlayers = new HashMap<Colour, Colour>();
		players.put(Colour.Black, null);
		nextPlayers.put(Colour.Black, null);

		spectators = new ArrayList<Spectator>();
	}

	/**
	 * Constructor used for loading a saved game
	 * 
	 * @param numberOfDetectives
	 * @param rounds
	 * @param graphFileName
	 * @param mrXLastLocation
	 * @param round
	 * @param mrXTicketLog
	 * @throws IOException
	 */
	public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds, String graphFileName, int mrXLastLocation,
			int round, List<Ticket> mrXTicketLog) throws IOException {
		this(numberOfDetectives, rounds, graphFileName);
		this.round = round;
		this.mrXLastLocation = mrXLastLocation;
		this.mrXTicketLog = mrXTicketLog;
	}

	@Override
	protected Move getPlayerMove(Colour colour) {
		List<Move> possible = validMoves(colour);
		Move selectedMove = players.get(colour).notify(locations.get(colour), possible);
		if (possible.contains(selectedMove))
			return selectedMove;
		return null;
	}

	@Override
	protected void nextPlayer() {
		currentPlayer = nextPlayers.get(currentPlayer);
	}

	/**
	 * @return Returns the colour of the next player without passing control
	 */
	protected Colour getNextPlayer() {
		return nextPlayers.get(currentPlayer);
	}

	@Override
	protected void play(MoveTicket move) {
		incrementRound(move.colour);
		passTicketToMrX(move.colour, move.ticket);
		updatePlayerLocation(move.colour, move.target);
		if (move.colour == Colour.Black)
			mrXTicketLog.add(move.ticket);
		for (Spectator spectator : spectators)
			spectator.notify(Utilities.hideMrXMove(move, mrXLastLocation));
	}

	@Override
	protected void play(MoveDouble move) {
		for (Spectator spectator : spectators) {
			spectator.notify(move);
		}
		passTicketToMrX(move.colour, Ticket.DoubleMove);
		play(move.moves.get(0));
		play(move.moves.get(1));
	}

	@Override
	protected void play(MovePass move) {
		for (Spectator spectator : spectators)
			spectator.notify(move);
		if (move.colour == Utilities.getKey(nextPlayers, Colour.Black)) {
		}

	}

	@Override
	protected List<Move> validMoves(Colour player) {
		Map<Ticket, Integer> ticketOffsets = new HashMap<Ticket, Integer>();
		ticketOffsets.put(Ticket.Taxi, 0);
		ticketOffsets.put(Ticket.Bus, 0);
		ticketOffsets.put(Ticket.Underground, 0);
		ticketOffsets.put(Ticket.SecretMove, 0);
		ticketOffsets.put(Ticket.DoubleMove, 0);
		int location = locations.get(player);
		return getValidMoves(player, location, false, ticketOffsets);
	}

	@Override
	public void spectate(Spectator spectator) {
		spectators.add(spectator);
	}

	@Override
	public boolean join(Player player, Colour colour, int location, Map<Ticket, Integer> tickets) {
		if (isReady)
			return false;
		players.put(colour, player);
		updatePlayerLocation(colour, location);
		this.tickets.put(colour, tickets);
		nextPlayers.put(currentPlayer, colour);
		nextPlayers.put(colour, null);
		currentPlayer = colour;
		if (players.size() - 1 == numberOfDetectives) {
			isReady = true;
			nextPlayers.put(colour, Colour.Black);
			currentPlayer = Colour.Black;
		}
		if (players.size() - 1 > numberOfDetectives)
			throw new Error("The game requires exactly one MrX and " + numberOfDetectives + " detectives.");
		return true;
	}

	@Override
	public List<Colour> getPlayers() {
		List<Colour> output = new ArrayList<Colour>();
		Set<Colour> keys = players.keySet();
		output.addAll(keys);
		return output;
	}

	@Override
	public Set<Colour> getWinningPlayers() {
		List<Move> mrXValidMoves = validMoves(Colour.Black);

		// Detective win check
		// Caught
		if (Utilities.checkForDuplicateValues(locations)) {
			winReason = WinReason.MrXCaught;
			return getDetectives();
		}
		// Surrounded
		if (mrXValidMoves.isEmpty()) {
			winReason = WinReason.MrXSurrounded;
			return getDetectives();
		}

		// MrX win check
		List<Move> detectivesValidMoves = new ArrayList<Move>();
		getDetectives().forEach((detective) -> detectivesValidMoves.addAll(validMoves(detective))); // lambda

		// Detectives have no tickets
		boolean allMovePass = true;
		for (Move move : detectivesValidMoves)
			allMovePass &= move instanceof MovePass;
		if (allMovePass) {
			winReason = WinReason.DetectivesOutOfTickets;
			return getMrX();
		}

		// No more rounds. MrX escaped
		if (round >= rounds.size() - 1 && getCurrentPlayer() == Colour.Black) {
			winReason = WinReason.NoMoreRounds;
			return getMrX();
		}

		// No Winners
		return new HashSet<Colour>();
	}

	/**
	 * @return the reason the game is over
	 */
	public WinReason getWinReason() {
		return winReason;
	}

	@Override
	public int getPlayerLocation(Colour colour) {
		if (colour == Colour.Black)
			return mrXLastLocation;
		return locations.get(colour);
	}

	/**
	 * 
	 * @return the real location of MrX
	 */
	public int getMrXRealLocation() {
		return locations.get(Colour.Black);
	}

	/**
	 * @return the mrXTicketLog
	 */
	public List<Ticket> getMrXTicketLog() {
		return mrXTicketLog;
	}

	/**
	 * Updates the player location. If it's MrX and rounds.get(round) is true,
	 * mrXLastLocation is also updated
	 * 
	 * @param colour
	 * @param location
	 */
	public void updatePlayerLocation(Colour colour, int location) {
		if (colour == Colour.Black && rounds.get(round))
			mrXLastLocation = location;
		locations.put(colour, location);
	}

	@Override
	public int getPlayerTickets(Colour colour, Ticket ticket) {
		return getPlayerTickets(colour).get(ticket);
	}

	/**
	 * 
	 * @param colour
	 * @return The tickets of the specified player
	 */
	public Map<Ticket, Integer> getPlayerTickets(Colour colour) {
		return tickets.get(colour);
	}

	@Override
	public boolean isGameOver() {
		if (!isReady)
			return false;
		Set<Colour> winners = getWinningPlayers();
		if (winners.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean isReady() {
		return isReady;
	}

	@Override
	public Colour getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public int getRound() {
		return round;
	}

	@Override
	public List<Boolean> getRounds() {
		return rounds;
	}

	/**
	 * @param currentPlayer
	 *            the currentPlayer to set
	 */
	public void setCurrentPlayer(Colour currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * 
	 * @return true if MrX is visible, false otherwise
	 */
	public boolean isMrXVisible() {
		return rounds.get(getRound());
	}

	/**
	 * Gets valid moves based on colour, location and whether a check for double
	 * moves is needed
	 * 
	 * @param colour
	 *            Colour of player to check
	 * @param location
	 *            Location of player to check
	 * @param doubleChecked
	 *            If true checks for double moves
	 * @param ticketOffsets
	 *            Initially 0.Used for double move checking
	 * @return valid moves
	 */
	private List<Move> getValidMoves(Colour colour, int location, boolean doubleChecked,
			Map<Ticket, Integer> ticketOffsets) {
		// Get MrX real location
		int MrXLocation = locations.get(Colour.Black);

		// Temporarily remove MrX from the location if a detective is playing
		// because nodes with MrX on them are valid moves
		if (colour != Colour.Black)
			locations.replace(Colour.Black, null);

		List<Move> moves = new ArrayList<Move>();
		List<Move> doubleMoves = new ArrayList<Move>();
		List<Edge<Integer, Route>> possibleEdges = graph.getEdges(location);

		// Loop through all the related edges
		for (Edge<Integer, Route> edge : possibleEdges) {
			// Check if one of the two ends of the edge is free
			if (!locations.containsValue(edge.target()) || !locations.containsValue(edge.source())) {
				// Get relevant ticket
				Ticket ticket = Ticket.fromRoute(edge.data());
				// Check if there are enough tickets for the move
				if (getPlayerTickets(colour, ticket) + ticketOffsets.get(ticket) > 0) {
					// Create the move and add it
					moves.add(new MoveTicket(colour, edge.other(location), ticket));
					// If there are enough secret move tickets, add the move
					// again
					// Don't add it again if it's a Boat route because it is
					// already added from the previous line
					if (getPlayerTickets(colour, Ticket.SecretMove) + ticketOffsets.get(Ticket.SecretMove) > 0
							&& edge.data() != Route.Boat)
						moves.add(new MoveTicket(colour, edge.other(location), Ticket.SecretMove));
				}
			}
		}
		// If double check is enabled, and the player has enough double tickets
		if (!doubleChecked && getPlayerTickets(colour, Ticket.DoubleMove) + ticketOffsets.get(Ticket.DoubleMove) > 0) {
			for (Move move : moves) {
				if (move instanceof MoveTicket)
					// Go through all the single moves, checking for valid
					// double moves
					doubleMoves.addAll(getValidMoves((MoveTicket) move, ticketOffsets));
			}
			moves.addAll(doubleMoves);
		}
		// Put MrX back in the locations map
		locations.replace(Colour.Black, MrXLocation);
		// If a detective has not valid moves, create a MovePass move
		if (colour == Colour.Black || moves.size() != 0)
			return moves;
		else {
			moves.add(new MovePass(colour));
			return moves;
		}
	}

	/**
	 * This is only called when double move check is needed
	 * 
	 * @param firstMove
	 *            The first move of the double Move
	 * @param ticketOffsets
	 *            Temporarily changes the tickets, so we can recursively check
	 *            for double moves
	 * @return List of double moves
	 */
	private List<Move> getValidMoves(MoveTicket firstMove, Map<Ticket, Integer> ticketOffsets) {
		// Temporarily make the first move
		int prevLocation = locations.get(firstMove.colour);
		locations.replace(firstMove.colour, firstMove.target);

		List<Move> output = new ArrayList<Move>();

		// Temporarily reduce the ticket number
		ticketOffsets.replace(firstMove.ticket, ticketOffsets.get(firstMove.ticket) - 1);

		// Get all the valid second moves
		List<Move> possibleSecondMoves = getValidMoves(firstMove.colour, firstMove.target, true, ticketOffsets);
		// Merge with the first move to create double moves
		for (Move secondMove : possibleSecondMoves) {
			output.add(new MoveDouble(firstMove.colour, firstMove, secondMove));
		}
		// Reset offsets and location for the next move
		ticketOffsets.replace(Ticket.Taxi, 0);
		ticketOffsets.replace(Ticket.Bus, 0);
		ticketOffsets.replace(Ticket.Underground, 0);
		ticketOffsets.replace(Ticket.SecretMove, 0);
		ticketOffsets.replace(Ticket.DoubleMove, 0);
		locations.replace(firstMove.colour, prevLocation);
		return output;
	}

	/**
	 * Removes ticket from player and gives it to MrX
	 * 
	 * @param colour
	 * @param ticket
	 */
	private void passTicketToMrX(Colour colour, Ticket ticket) {
		getPlayerTickets(colour).replace(ticket, getPlayerTickets(colour, ticket) - 1);
		if (ticket != Ticket.SecretMove && ticket != Ticket.DoubleMove)
			getPlayerTickets(Colour.Black).replace(ticket, getPlayerTickets(Colour.Black, ticket) + 1);
	}

	/**
	 * Increases the round counter by one each time MrX makes a move
	 *
	 * @param colour
	 *            Colour of the player that makes the move
	 * @return A boolean that indicates if the round was increased
	 */
	private void incrementRound(Colour colour) {
		if (round == rounds.size() - 1)
			return;
		if (colour == Colour.Black) {
			round++;
		}
	}

	/**
	 * 
	 * @return a set containing all the detectives
	 */
	private Set<Colour> getDetectives() {
		Set<Colour> allDetectives = new HashSet<Colour>(players.keySet());
		allDetectives.remove(Colour.Black);
		return allDetectives;
	}

	/**
	 * 
	 * @return a set containing only MrX
	 */
	private Set<Colour> getMrX() {
		Set<Colour> mrX = new HashSet<Colour>();
		mrX.add(Colour.Black);
		return mrX;
	}

	/**
	 * 
	 * @return a map containing all the names
	 */
	public Map<Colour, String> getNames() {
		Map<Colour, String> output = new HashMap<Colour, String>();
		for (Map.Entry<Colour, Player> entry : players.entrySet()) {
			if (entry.getKey() != Colour.Black) {
				output.put(entry.getKey(), ((Detective) entry.getValue()).getName());
			}
		}
		return output;
	}

	@Override
	public String toString() {
		String s = "";
		// firstLine
		s += mrXLastLocation + ",";
		s += round + ",";
		s += currentPlayer + "\n";

		for (Ticket ticket : mrXTicketLog) {
			s += ticket + ",";
		}
		s += "\n";

		// Rest of lines
		for (Map.Entry<Colour, Player> player : players.entrySet()) {
			Player pl = player.getValue();
			AbstractPlayer ap = (AbstractPlayer) pl;
			s += ap.toString(tickets.get(player.getKey()), locations.get(player.getKey())) + "\n";
		}

		return s;
	}

	@Override
	public void run() {
		start();
	}

	/**
	 * Stops the thread
	 */
	public void stop() {
		isReady = false;
		AbstractPlayer ap = (AbstractPlayer) players.get(getCurrentPlayer());
		try {
			ap.moveIn.put(new MovePass(getCurrentPlayer()));
		} catch (InterruptedException e) {
			stop();
		}
	}

	enum WinReason {
		None, MrXCaught, MrXSurrounded, DetectivesOutOfTickets, NoMoreRounds;

		@Override
		public String toString() {
			switch (this) {
			case MrXCaught:
				return "The detectives were able to find and arrest MrX!";
			case MrXSurrounded:
				return "MrX has been surrounded and has nowhere to go!";
			case DetectivesOutOfTickets:
				return "The detectives have run out of tickets!";
			case NoMoreRounds:
				return "There are no more rounds. MrX has escaped!";
			default:
				return "";
			}
		}
	}

}
