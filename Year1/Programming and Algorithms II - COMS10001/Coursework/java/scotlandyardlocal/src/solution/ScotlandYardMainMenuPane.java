package solution;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import solution.MigLayout.MigLayout;

public class ScotlandYardMainMenuPane extends JPanel {

	private static final long serialVersionUID = -6897368877884537893L;
	Image background;
	JButton playButton;
	JButton loadButton;
	JButton exitButton;

	public ScotlandYardMainMenuPane() {

		setPreferredSize(new Dimension(1406, 827));

		URL backURL = this.getClass().getResource("resources/back.jpg");

		URL playURL = this.getClass().getResource("resources/playIcon.png");
		URL playPressedURL = this.getClass().getResource("resources/playPressed.png");

		URL loadURL = this.getClass().getResource("resources/loadIcon.png");
		URL loadPressedURL = this.getClass().getResource("resources/loadPressed.png");

		URL exitURL = this.getClass().getResource("resources/exitIcon.png");
		URL exitPressedURL = this.getClass().getResource("resources/exitPressed.png");

		ImageIcon playIcon = new ImageIcon(playURL);
		ImageIcon playPressed = new ImageIcon(playPressedURL);
		ImageIcon loadIcon = new ImageIcon(loadURL);
		ImageIcon loadPressed = new ImageIcon(loadPressedURL);
		ImageIcon exitIcon = new ImageIcon(exitURL);
		ImageIcon exitPressed = new ImageIcon(exitPressedURL);
		background = new ImageIcon(backURL).getImage();

		// Create buttons
		// Play button
		playButton = new JButton(playIcon);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setBorder(null);
		playButton.setPressedIcon(playPressed);

		// Load button
		loadButton = new JButton("", loadIcon);
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		loadButton.setBorder(null);
		loadButton.setPressedIcon(loadPressed);

		// Exit button
		exitButton = new JButton("", exitIcon);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setBorder(null);
		exitButton.setPressedIcon(exitPressed);

		playButton.setActionCommand("play");
		loadButton.setActionCommand("load");
		exitButton.setActionCommand("exit");

		setLayout(new MigLayout("", "[235.00px][281px][281px][281px]", "[374.00][118.00][161.00]"));
		add(playButton, "cell 1 1,alignx center,aligny center");
		add(loadButton, "cell 3 1,grow");
		add(exitButton, "cell 2 2,grow");
	}

	/**
	 * Method called by the controller to add listeners
	 * 
	 * @param al
	 *            Listener
	 */
	public void addMenuListener(ActionListener al) {
		playButton.addActionListener(al);
		loadButton.addActionListener(al);
		exitButton.addActionListener(al);
	}

	@Override
	public void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		g.drawImage(background, 0, 0, null);
	}

}
