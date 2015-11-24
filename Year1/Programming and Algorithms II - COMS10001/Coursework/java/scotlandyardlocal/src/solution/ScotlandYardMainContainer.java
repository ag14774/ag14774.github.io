package solution;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import solution.ScotlandYardModel.WinReason;

/**
 * 
 * Main frame
 */
public class ScotlandYardMainContainer extends JFrame {

	private static final long serialVersionUID = 6460764425082596548L;
	private static final String songFile = "resources/song.wav";
	private static final String detectivesWinSound = "resources/underarrest.wav";
	private static final String mrXWinsSound = "resources/gameover.wav";
	private static final String mrXWinsIcon = "/solution/resources/mrXWinIcon.png";
	private static final String detectivesWinIcon = "/solution/resources/detectivesWinIcon.png";

	private ScotlandYardMainMenuPane mainMenuPanel;
	private ScotlandYardNewGameScreen newGameDialog;
	private ScotlandYardMapGUI gameView;
	private ImageIcon mrXWins;
	private ImageIcon detectivesWin;
	private FloatControl volumeControl;
	private boolean songMuted;
	private WinReason winReason;
	boolean listenerActive;

	/**
	 * Constructor for ScotlandYardMainContainer
	 */
	public ScotlandYardMainContainer() {
		songMuted = false;
		URL song = this.getClass().getResource(songFile);
		volumeControl = Utilities.SoundUtilities.loopSound(song);
		volumeControl.setValue(-10.0f);
		winReason = WinReason.None;
		mrXWins = new ImageIcon(ScotlandYardMainContainer.class.getResource(mrXWinsIcon));
		detectivesWin = new ImageIcon(ScotlandYardMainContainer.class.getResource(detectivesWinIcon));
		setResizable(false);

		/**
		 * The glass pane is used to draw the winner and why
		 */
		JPanel glassPane = new JPanel() {
			private static final long serialVersionUID = 459553787344592880L;

			@Override
			public void paintComponent(Graphics g0) {
				Graphics2D g = (Graphics2D) g0;
				RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHints(rh);
				// Draw the image always in centre of map
				switch (winReason) {
				case DetectivesOutOfTickets:
					g.drawImage(mrXWins.getImage(), (1018 - mrXWins.getIconWidth()) / 2, 200, null);
					break;
				case MrXCaught:
					g.drawImage(detectivesWin.getImage(), (1018 - detectivesWin.getIconWidth()) / 2, 200, null);
					break;
				case MrXSurrounded:
					g.drawImage(detectivesWin.getImage(), (1018 - detectivesWin.getIconWidth()) / 2, 200, null);
					break;
				case NoMoreRounds:
					g.drawImage(mrXWins.getImage(), (1018 - mrXWins.getIconWidth()) / 2, 200, null);
					break;
				default:
					break;
				}
				// Draw the reason the game is over
				if (winReason != WinReason.None) {
					g.setFont(new Font("Courier New", Font.BOLD, 22));
					g.setColor(Color.black);
					g.drawString(winReason.toString(), (1018 - winReason.toString().length() * 13) / 2, 450);
				}
			}
		};
		// Make the glass transparent and add a listener which will consume all
		// inputs without passing them to the main container
		// Initially the listener is disabled
		glassPane.setOpaque(false);
		setGlassPane(glassPane);
		listenerActive = false;
		getGlassPane().addMouseListener(new ConsumeInputsListener());
	}

	/**
	 * @return the gameView
	 */
	public ScotlandYardMapGUI getGameView() {
		return gameView;
	}

	/**
	 * Make glass pane visible and enable the listener
	 */
	public void showAndRepaintGlass() {
		getGlassPane().setVisible(true);
		getGlassPane().repaint();
		volumeControl.setValue(-25.0f);
		listenerActive = true;
		if (winReason == WinReason.DetectivesOutOfTickets || winReason == WinReason.NoMoreRounds) {
			URL sound = this.getClass().getResource(mrXWinsSound);
			Utilities.SoundUtilities.playSound(sound);
		} else if (winReason == WinReason.MrXCaught || winReason == WinReason.MrXSurrounded) {
			URL sound = this.getClass().getResource(detectivesWinSound);
			Utilities.SoundUtilities.playSound(sound);
		}

	}

	/**
	 * Mute/Unmute background music
	 */
	public void toggleSong() {
		if (!songMuted) {
			volumeControl.setValue(-80.0f);
			songMuted = true;
		} else {
			volumeControl.setValue(-10.0f);
			songMuted = false;
		}
	}

	/**
	 * 
	 * @return the New Game dialog object that holds all relevant data
	 */
	public ScotlandYardNewGameScreen getDialogObject() {
		return newGameDialog;
	}

	/**
	 * 
	 * @return the Main Menu panel
	 */
	public ScotlandYardMainMenuPane getMainMenuPanel() {
		return mainMenuPanel;
	}

	/**
	 * Set up frame as a main menu
	 */
	public void setUpMainMenu() {
		getContentPane().removeAll();
		volumeControl.setValue(-10.0f);
		songMuted = false;
		mainMenuPanel = new ScotlandYardMainMenuPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainMenuPanel, BorderLayout.SOUTH);
		setLocationByPlatform(true);
		setTitle("Scotland Yard Digital: Main Menu");
		pack();
	}

	/**
	 * Set up frame as a new game
	 */
	public void setUpGameGUI() {
		getContentPane().removeAll();
		gameView = new ScotlandYardMapGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gameView);
		setLocationByPlatform(true);
		setTitle("Scotland Yard Digital");
		pack();
	}

	/**
	 * @param winReason
	 *            the winReason to set
	 */
	public void setWinReason(WinReason winReason) {
		this.winReason = winReason;
	}

	/**
	 * Show new game configuration screen
	 * 
	 * @param parent
	 * @param modal
	 */
	public void showNewGameDialog(JFrame parent, boolean modal) {
		newGameDialog = new ScotlandYardNewGameScreen(parent, modal);
		newGameDialog.setVisible(true);
	}

	/**
	 * Show an error message
	 * 
	 * @param parent
	 * @param error
	 */
	public void showErrorMessage(JFrame parent, String error) {
		JOptionPane.showMessageDialog(parent, error);
	}

	/**
	 * show a confirmation dialog
	 * 
	 * @param parent
	 * @param message
	 * @param type
	 * @return an integer representing the selected option
	 */
	public int showConfirmDialog(JFrame parent, String message, int type) {
		return JOptionPane.showConfirmDialog(parent, message, "Choose an option:", type);
	}

	/**
	 * Show a file chooser.
	 * 
	 * @param parent
	 *            Parent frame
	 * @param title
	 *            Title of the window
	 * @param save
	 *            Set to true if a save dialog is needed. False otherwise
	 * @return the selected file. <i>null</i> if cancel or invalid file was
	 *         selected
	 */
	public File showFileChooser(JFrame parent, String title, boolean save) {
		String path = "";
		JFileChooser jchooser = new JFileChooser();
		SYGFileFilter filefilter = new SYGFileFilter();
		jchooser.setFileFilter(filefilter);
		int returnValue;
		while (path.equals("")) {
			if (save)
				returnValue = jchooser.showSaveDialog(parent);
			else
				returnValue = jchooser.showOpenDialog(parent);
			jchooser.setDialogTitle(title);
			if (jchooser.getSelectedFile() != null)
				path = jchooser.getSelectedFile().getName();
			else
				return null;
			if (returnValue == JFileChooser.CANCEL_OPTION)
				return null;
		}
		if (!jchooser.getSelectedFile().getName().endsWith(filefilter.fileExt)
				&& !jchooser.getSelectedFile().getName().endsWith(".txt"))
			return new File(jchooser.getSelectedFile().getAbsolutePath() + filefilter.fileExt);
		else
			return jchooser.getSelectedFile();
	}

	/**
	 * 
	 * A file extension filter. Only accepts .syg files(Scotland Yard Game
	 * files)
	 *
	 */
	class SYGFileFilter extends FileFilter {

		String description;
		String fileExt;

		SYGFileFilter() {
			description = "Scotland Yard save files (.syg)";
			fileExt = ".syg";
		}

		@Override
		public boolean accept(File file) {
			if (file.isDirectory())
				return true;
			return file.getName().toLowerCase().endsWith(fileExt);
		}

		@Override
		public String getDescription() {
			return description;
		}

	}

	/**
	 * 
	 * Consumes all mouse inputs, to block all the buttons from accepting input
	 * Enabled when a player wins
	 *
	 */
	class ConsumeInputsListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (listenerActive)
				arg0.consume();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

}
