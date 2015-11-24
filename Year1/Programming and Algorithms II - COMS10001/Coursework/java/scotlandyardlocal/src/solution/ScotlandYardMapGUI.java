package solution;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.*;
import java.util.List;

import javax.swing.*;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.Ticket;
import solution.MigLayout.MigLayout;
//import net.miginfocom.swing.MigLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class ScotlandYardMapGUI extends JPanel {

	private static final long serialVersionUID = 3632365674517867743L;
	final static private String mapFileName = "/solution/resources/map.jpg";
	final static private String greenPin = "/solution/resources/greenPin32.png";
	final static private String bluePin = "/solution/resources/bluePin32.png";
	final static private String redPin = "/solution/resources/redPin32.png";
	final static private String yellowPin = "/solution/resources/yellowPin32.png";
	final static private String whitePin = "/solution/resources/whitePin32.png";
	final static private String blackPin = "/solution/resources/blackPin32.png";
	final static private String moveMask = "/solution/resources/moveMask.png";

	protected Map<Colour, List<Integer>> knownLocations;
	protected Set<List<Integer>> validCoordinates;
	protected List<Integer> mouseLocation;
	private int round;
	private JPanel mapPanel;
	private JPanel buttonPanel;
	private JButton btnMrXRevealLocation;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnMain;
	private JButton btnExit;
	private JToggleButton tglMute;
	private JLabel lblBlueTaxi;
	private JLabel lblBlueBus;
	private JLabel lblBlueUnderground;
	private JLabel lblGreenTaxi;
	private JLabel lblGreenBus;
	private JLabel lblGreenUnderground;
	private JLabel lblRedTaxi;
	private JLabel lblRedBus;
	private JLabel lblRedUnderground;
	private JLabel lblWhiteTaxi;
	private JLabel lblWhiteBus;
	private JLabel lblWhiteUnderground;
	private JLabel lblYellowTaxi;
	private JLabel lblYellowBus;
	private JLabel lblYellowUnderground;
	private JLabel lblBlackSecretMove;
	private JLabel lblBlackDoubleMove;
	private JLabel lblLastNum;
	private JLabel lblYellowName;
	private JLabel lblWhiteName;
	private JLabel lblRedName;
	private JLabel lblGreenName;
	private JLabel lblBlueName;
	private JLabel lblMrxTravelLog;
	private JLabel lblRound1;
	private JLabel lblRound2;
	private JLabel lblRound3;
	private JLabel lblRound4;
	private JLabel lblRound5;
	private JLabel lblRound6;
	private JLabel lblRound7;
	private JLabel lblRound8;
	private JLabel lblRound9;
	private JLabel lblRound10;
	private JLabel lblRound11;
	private JLabel lblRound12;
	private JLabel lblRound13;
	private JLabel lblRound14;
	private JLabel lblRound15;
	private JLabel lblRound16;
	private JLabel lblRound17;
	private JLabel lblRound18;
	private JLabel lblRound19;
	private JLabel lblRound20;
	private JLabel lblRound21;
	private JLabel lblRound22;
	private JLabel iconRound1;
	private JLabel iconRound2;
	private JLabel iconRound3;
	private JLabel iconRound4;
	private JLabel iconRound5;
	private JLabel iconRound6;
	private JLabel iconRound7;
	private JLabel iconRound8;
	private JLabel iconRound9;
	private JLabel iconRound10;
	private JLabel iconRound11;
	private JLabel iconRound12;
	private JLabel iconRound13;
	private JLabel iconRound14;
	private JLabel iconRound15;
	private JLabel iconRound16;
	private JLabel iconRound17;
	private JLabel iconRound18;
	private JLabel iconRound19;
	private JLabel iconRound20;
	private JLabel iconRound21;
	private JLabel iconRound22;
	private MovePopUp movePop;
	private ImageIcon mapIcon;
	private ImageIcon greenPinIcon;
	private ImageIcon redPinIcon;
	private ImageIcon whitePinIcon;
	private ImageIcon yellowPinIcon;
	private ImageIcon bluePinIcon;
	private ImageIcon blackPinIcon;
	private ImageIcon moveMaskIcon;

	public ScotlandYardMapGUI() {
		setBackground(new Color(0, 0, 0));
		initialiseResources();
		setPreferredSize(new Dimension(1389, 833));
		setLayout(new MigLayout("", "[1028px][]", "[819px]"));

		mapPanel = new JPanel() {
			private static final long serialVersionUID = 6587742216549325384L;

			@Override
			public void paintComponent(Graphics g0) {
				super.paintComponent(g0);
				Graphics2D g = (Graphics2D) g0;
				RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHints(rh);
				g.drawImage(mapIcon.getImage(), 5, 5, null);
				if (validCoordinates != null) {
					for (List<Integer> coordinate : validCoordinates) {
						if (coordinate != null && !coordinate.isEmpty()) {
							g.drawImage(moveMaskIcon.getImage(), coordinate.get(0) - 6, coordinate.get(1) - 7, null);
						}
					}
				}
				if (mouseLocation != null) {
					g.drawImage(moveMaskIcon.getImage(), mouseLocation.get(0) - 6, mouseLocation.get(1) - 7, null);
				}
				if (knownLocations != null) {
					for (Map.Entry<Colour, List<Integer>> entry : knownLocations.entrySet()) {
						if (entry.getValue() != null) {
							int x = entry.getValue().get(0) - 10;
							int y = entry.getValue().get(1) - 30;
							switch (entry.getKey()) {
							case Black:
								g.drawImage(blackPinIcon.getImage(), x, y, null);
								break;
							case Yellow:
								g.drawImage(yellowPinIcon.getImage(), x, y, null);
								break;
							case White:
								g.drawImage(whitePinIcon.getImage(), x, y, null);
								break;
							case Blue:
								g.drawImage(bluePinIcon.getImage(), x, y, null);
								break;
							case Red:
								g.drawImage(redPinIcon.getImage(), x, y, null);
								break;
							case Green:
								g.drawImage(greenPinIcon.getImage(), x, y, null);
								break;
							}
						}
					}
				}
				g.setFont(new Font("Courier New", Font.BOLD, 15));
				g.setColor(Color.yellow);
				g.drawString("Round: " + round, 13, 20);
			}
		};

		createMap();

		JPanel contentPanel = createSidePanel();

		createMrXPanel(contentPanel);

		createDetectivePanel(contentPanel);

		createMrXTravelLog(contentPanel);

		createButtonPanel(contentPanel);

		setVisible(true);
	}

	/**
	 * 
	 */
	private void initialiseResources() {
		knownLocations = new HashMap<Colour, List<Integer>>();
		validCoordinates = new HashSet<List<Integer>>();
		mapIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(mapFileName));
		greenPinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(greenPin));
		redPinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(redPin));
		yellowPinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(yellowPin));
		bluePinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(bluePin));
		whitePinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(whitePin));
		blackPinIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(blackPin));
		moveMaskIcon = new ImageIcon(ScotlandYardMapGUI.class.getResource(moveMask));
	}

	/**
	 * @param contentPanel
	 */
	private void createButtonPanel(JPanel contentPanel) {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 153, 255));
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(buttonPanel, "cell 0 3,grow");
		buttonPanel.setLayout(new MigLayout("", "[grow 5][grow 5]", "[grow 1][grow 1]"));

		btnSave = new JButton("Save Game");
		btnSave.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnSave.setActionCommand("save");

		btnMain = new JButton("Main  Menu");
		btnMain.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnMain.setActionCommand("back");

		btnLoad = new JButton("Load Game");
		btnLoad.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLoad.setActionCommand("load");

		btnExit = new JButton("   Exit   ");
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnExit.setActionCommand("exit");

		buttonPanel.add(btnSave, "cell 0 0,alignx center,aligny center");
		buttonPanel.add(btnMain, "cell 1 0,alignx center,aligny center");
		buttonPanel.add(btnLoad, "cell 0 1,alignx center,aligny center");
		buttonPanel.add(btnExit, "cell 1 1,alignx center,aligny center");
	}

	/**
	 * @param contentPanel
	 */
	private void createMrXTravelLog(JPanel contentPanel) {
		JPanel mrXTravelLogPanel = new JPanel();
		mrXTravelLogPanel.setBackground(new Color(0, 153, 255));
		mrXTravelLogPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(mrXTravelLogPanel, "cell 0 2,growy");
		mrXTravelLogPanel
				.setLayout(new MigLayout(
						"",
						"[19.00][][46.00][129.00]",
						"[24.00][grow 7,fill][grow 7,fill][grow 7,fill][grow 7,fill][grow 7,fill][grow 7,fill][grow 7,fill][grow 7][grow 7,fill][grow 7,fill][grow 7,fill]"));

		lblMrxTravelLog = new JLabel("Mr X Travel Log");
		lblMrxTravelLog.setFont(new Font("Orator Std", Font.BOLD, 12));
		mrXTravelLogPanel.add(lblMrxTravelLog, "cell 0 0 2 1");

		lblRound1 = new JLabel("1.");
		lblRound1.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound1, "cell 0 1,alignx right");

		iconRound1 = new JLabel("");
		mrXTravelLogPanel.add(iconRound1, "cell 1 1");

		lblRound12 = new JLabel("12.");
		lblRound12.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound12, "cell 2 1,alignx right");

		iconRound12 = new JLabel("");
		mrXTravelLogPanel.add(iconRound12, "cell 3 1");

		lblRound2 = new JLabel("2.");
		lblRound2.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound2, "cell 0 2,alignx right");

		iconRound2 = new JLabel("");
		mrXTravelLogPanel.add(iconRound2, "cell 1 2");

		lblRound13 = new JLabel("13.");
		lblRound13.setForeground(Color.BLUE);
		lblRound13.setFont(new Font("Orator Std", Font.BOLD, 12));
		mrXTravelLogPanel.add(lblRound13, "cell 2 2,alignx right");

		iconRound13 = new JLabel("");
		mrXTravelLogPanel.add(iconRound13, "cell 3 2");

		lblRound3 = new JLabel("3.");
		lblRound3.setForeground(Color.BLUE);
		lblRound3.setFont(new Font("Orator Std", Font.BOLD, 12));
		mrXTravelLogPanel.add(lblRound3, "cell 0 3,alignx right");

		iconRound3 = new JLabel("");
		mrXTravelLogPanel.add(iconRound3, "cell 1 3");

		lblRound14 = new JLabel("14.");
		lblRound14.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound14, "cell 2 3,alignx right");

		iconRound14 = new JLabel("");
		mrXTravelLogPanel.add(iconRound14, "cell 3 3");

		lblRound4 = new JLabel("4.");
		lblRound4.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound4, "cell 0 4,alignx right");

		iconRound4 = new JLabel("");
		mrXTravelLogPanel.add(iconRound4, "cell 1 4");

		lblRound15 = new JLabel("15.");
		lblRound15.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound15, "cell 2 4,alignx right");

		iconRound15 = new JLabel("");
		mrXTravelLogPanel.add(iconRound15, "cell 3 4");

		lblRound5 = new JLabel("5.");
		lblRound5.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound5, "cell 0 5,alignx right");

		iconRound5 = new JLabel("");
		mrXTravelLogPanel.add(iconRound5, "cell 1 5");

		lblRound16 = new JLabel("16.");
		lblRound16.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound16, "cell 2 5,alignx right");

		iconRound16 = new JLabel("");
		mrXTravelLogPanel.add(iconRound16, "cell 3 5");

		lblRound6 = new JLabel("6.");
		lblRound6.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound6, "cell 0 6,alignx right");

		iconRound6 = new JLabel("");
		mrXTravelLogPanel.add(iconRound6, "cell 1 6");

		lblRound17 = new JLabel("17.");
		lblRound17.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound17, "cell 2 6,alignx right");

		iconRound17 = new JLabel("");
		mrXTravelLogPanel.add(iconRound17, "cell 3 6");

		lblRound7 = new JLabel("7.");
		lblRound7.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound7, "cell 0 7,alignx right");

		iconRound7 = new JLabel("");
		mrXTravelLogPanel.add(iconRound7, "cell 1 7");

		lblRound18 = new JLabel("18.");
		lblRound18.setForeground(Color.BLUE);
		lblRound18.setFont(new Font("Orator Std", Font.BOLD, 12));
		mrXTravelLogPanel.add(lblRound18, "cell 2 7,alignx right");

		iconRound18 = new JLabel("");
		mrXTravelLogPanel.add(iconRound18, "cell 3 7");

		lblRound8 = new JLabel("8.");
		lblRound8.setForeground(Color.BLUE);
		lblRound8.setFont(new Font("Orator Std", Font.BOLD, 12));
		mrXTravelLogPanel.add(lblRound8, "cell 0 8,alignx right");

		iconRound8 = new JLabel("");
		mrXTravelLogPanel.add(iconRound8, "cell 1 8");

		lblRound19 = new JLabel("19.");
		lblRound19.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound19, "cell 2 8,alignx right");

		iconRound19 = new JLabel("");
		mrXTravelLogPanel.add(iconRound19, "cell 3 8");

		lblRound9 = new JLabel("9.");
		lblRound9.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound9, "cell 0 9,alignx right");

		iconRound9 = new JLabel("");
		mrXTravelLogPanel.add(iconRound9, "cell 1 9");

		lblRound20 = new JLabel("20.");
		lblRound20.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound20, "cell 2 9,alignx right");

		iconRound20 = new JLabel("");
		mrXTravelLogPanel.add(iconRound20, "cell 3 9");

		lblRound10 = new JLabel("10.");
		lblRound10.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound10, "cell 0 10,alignx right");

		iconRound10 = new JLabel("");
		mrXTravelLogPanel.add(iconRound10, "cell 1 10");

		lblRound21 = new JLabel("21.");
		lblRound21.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound21, "cell 2 10,alignx right");

		iconRound21 = new JLabel("");
		mrXTravelLogPanel.add(iconRound21, "cell 3 10");

		lblRound11 = new JLabel("11.");
		lblRound11.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound11, "cell 0 11,alignx right");

		iconRound11 = new JLabel("");
		mrXTravelLogPanel.add(iconRound11, "cell 1 11");

		lblRound22 = new JLabel("22.");
		lblRound22.setFont(new Font("Orator Std", Font.PLAIN, 12));
		mrXTravelLogPanel.add(lblRound22, "cell 2 11,alignx right");

		iconRound22 = new JLabel("");
		mrXTravelLogPanel.add(iconRound22, "cell 3 11");
	}

	/**
	 * @param contentPanel
	 */
	private void createDetectivePanel(JPanel contentPanel) {
		JPanel detectivesPanel = new JPanel();
		detectivesPanel.setBackground(new Color(0, 153, 255));
		detectivesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(detectivesPanel, "cell 0 1,grow");
		detectivesPanel.setLayout(new MigLayout("", "[50.00][113.00][32.00][19.00][]", "[23.00][26.00][][][][][]"));

		JLabel lblDetectivesStatus = new JLabel("Detectives Status");
		lblDetectivesStatus.setFont(new Font("Orator Std", Font.BOLD, 12));
		detectivesPanel.add(lblDetectivesStatus, "cell 0 0 4 1");

		JLabel lblTaxiIcon = new JLabel("");
		lblTaxiIcon.setIcon(new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/taxi16.png")));
		detectivesPanel.add(lblTaxiIcon, "cell 2 1,alignx center");

		JLabel lblBusIcon = new JLabel("");
		lblBusIcon.setIcon(new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/bus.png")));
		detectivesPanel.add(lblBusIcon, "cell 3 1,alignx center");

		JLabel lblUnderIcon = new JLabel("");
		lblUnderIcon.setIcon(new ImageIcon(ScotlandYardMapGUI.class
				.getResource("/solution/resources/underground32.png")));
		detectivesPanel.add(lblUnderIcon, "cell 4 1,alignx center");

		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblBlue, "cell 0 2");

		lblBlueName = new JLabel("No Name");
		lblBlueName.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblBlueName, "cell 1 2");

		lblBlueTaxi = new JLabel("0");
		lblBlueTaxi.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblBlueTaxi, "cell 2 2,alignx center");

		lblBlueBus = new JLabel("0");
		lblBlueBus.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblBlueBus, "cell 3 2,alignx center");

		lblBlueUnderground = new JLabel("0");
		lblBlueUnderground.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblBlueUnderground, "cell 4 2,alignx center");

		JLabel lblGreen = new JLabel("Green");
		lblGreen.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblGreen, "cell 0 3");

		lblGreenName = new JLabel("No Name");
		lblGreenName.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblGreenName, "cell 1 3");

		lblGreenTaxi = new JLabel("0");
		lblGreenTaxi.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblGreenTaxi, "cell 2 3,alignx center");

		lblGreenBus = new JLabel("0");
		lblGreenBus.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblGreenBus, "cell 3 3,alignx center");

		lblGreenUnderground = new JLabel("0");
		lblGreenUnderground.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblGreenUnderground, "cell 4 3,alignx center");

		JLabel lblRed = new JLabel("Red");
		lblRed.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblRed, "cell 0 4");

		lblRedName = new JLabel("No Name");
		lblRedName.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblRedName, "cell 1 4");

		lblRedTaxi = new JLabel("0");
		lblRedTaxi.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblRedTaxi, "cell 2 4,alignx center");

		lblRedBus = new JLabel("0");
		lblRedBus.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblRedBus, "cell 3 4,alignx center");

		lblRedUnderground = new JLabel("0");
		lblRedUnderground.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblRedUnderground, "cell 4 4,alignx center");

		JLabel lblWhite = new JLabel("White");
		lblWhite.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblWhite, "cell 0 5");

		lblWhiteName = new JLabel("No Name");
		lblWhiteName.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblWhiteName, "cell 1 5");

		lblWhiteTaxi = new JLabel("0");
		lblWhiteTaxi.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblWhiteTaxi, "cell 2 5,alignx center");

		lblWhiteBus = new JLabel("0");
		lblWhiteBus.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblWhiteBus, "cell 3 5,alignx center");

		lblWhiteUnderground = new JLabel("0");
		lblWhiteUnderground.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblWhiteUnderground, "cell 4 5,alignx center");

		JLabel lblYellow = new JLabel("Yellow");
		lblYellow.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblYellow, "cell 0 6");

		lblYellowName = new JLabel("No Name");
		lblYellowName.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblYellowName, "cell 1 6");

		lblYellowTaxi = new JLabel("0");
		lblYellowTaxi.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblYellowTaxi, "cell 2 6,alignx center");

		lblYellowBus = new JLabel("0");
		lblYellowBus.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblYellowBus, "cell 3 6,alignx center");

		lblYellowUnderground = new JLabel("0");
		lblYellowUnderground.setFont(new Font("Orator Std", Font.PLAIN, 13));
		detectivesPanel.add(lblYellowUnderground, "cell 4 6,alignx center");
	}

	/**
	 * @param mrXPanel
	 */
	private void createMuteButton(JPanel mrXPanel) {
		tglMute = new JToggleButton("");
		tglMute.setRequestFocusEnabled(false);
		tglMute.setRolloverEnabled(false);
		tglMute.setOpaque(true);
		tglMute.setContentAreaFilled(false);
		tglMute.setBorderPainted(false);
		tglMute.setFocusable(false);
		tglMute.setSelectedIcon(new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/mute.png")));
		tglMute.setIcon(new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/unmute.png")));
		tglMute.setBorder(null);
		tglMute.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tglMute.setActionCommand("mute");
		mrXPanel.add(tglMute, "cell 4 0 4 1,alignx right,aligny top");
	}

	/**
	 * @param contentPanel
	 * @return
	 */
	private void createMrXPanel(JPanel contentPanel) {
		JPanel mrXPanel = new JPanel();
		mrXPanel.setBackground(new Color(0, 153, 255));
		mrXPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(mrXPanel, "cell 0 0,grow");
		mrXPanel.setLayout(new MigLayout("", "[][16.00][][28.00][]", "[][-3.00][][25.00][][]"));

		JLabel lblmrX = new JLabel("Mr X Status");
		lblmrX.setFont(new Font("Orator Std", Font.BOLD, 13));
		mrXPanel.add(lblmrX, "flowx,cell 0 0 5 1");

		Box ticketIconBox = Box.createHorizontalBox();
		mrXPanel.add(ticketIconBox, "flowx,cell 2 2,alignx center");

		JLabel lblSecretMove = new JLabel("");
		ticketIconBox.add(lblSecretMove);
		lblSecretMove.setIcon(new ImageIcon(ScotlandYardMapGUI.class
				.getResource("/solution/resources/secretMove20.png")));

		Component horizontalStrut = Box.createHorizontalStrut(25);
		ticketIconBox.add(horizontalStrut);

		JLabel lblDouble = new JLabel("");
		ticketIconBox.add(lblDouble);
		lblDouble.setIcon(new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/doubleMove20.png")));

		JLabel lblTickets = new JLabel("Tickets");
		lblTickets.setFont(new Font("Orator Std", Font.PLAIN, 13));
		mrXPanel.add(lblTickets, "cell 0 3");

		Box ticketNumBox = Box.createHorizontalBox();
		mrXPanel.add(ticketNumBox, "flowx,cell 2 3,alignx center");

		lblBlackSecretMove = new JLabel("0");
		lblBlackSecretMove.setFont(new Font("Orator Std", Font.PLAIN, 13));
		ticketNumBox.add(lblBlackSecretMove);

		Component horizontalStrut2 = Box.createHorizontalStrut(38);
		ticketNumBox.add(horizontalStrut2);

		lblBlackDoubleMove = new JLabel("0");
		lblBlackDoubleMove.setFont(new Font("Orator Std", Font.PLAIN, 13));
		ticketNumBox.add(lblBlackDoubleMove);

		JLabel lblLastLocation = new JLabel("Last Location");
		lblLastLocation.setFont(new Font("Orator Std", Font.PLAIN, 13));
		mrXPanel.add(lblLastLocation, "cell 0 4");

		lblLastNum = new JLabel("0");
		lblLastNum.setFont(new Font("Orator Std", Font.PLAIN, 13));
		mrXPanel.add(lblLastNum, "cell 2 4,alignx center");

		btnMrXRevealLocation = new JButton("Reveal");
		btnMrXRevealLocation.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnMrXRevealLocation.setActionCommand("hidereveal");

		createMuteButton(mrXPanel);

		mrXPanel.add(btnMrXRevealLocation, "cell 4 5");
	}

	/**
	 * @return
	 */
	private JPanel createSidePanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.GRAY);
		add(contentPanel, "cell 1 0,grow");
		contentPanel.setLayout(new MigLayout("", "[grow,fill]", "[123.00][][491.00][121.00]"));
		return contentPanel;
	}

	/**
	 * Creates the map panel
	 */
	private void createMap() {
		mapPanel.setPreferredSize(new Dimension(1200, 1000));
		mapPanel.setBackground(Color.GRAY);
		FlowLayout flowLayout = (FlowLayout) mapPanel.getLayout();
		flowLayout.setHgap(2);
		mapPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(mapPanel, "cell 0 0,alignx left,aligny top");
	}

	/**
	 * Method called by the controller to add a listener to all the buttons
	 * 
	 * @param al
	 */
	public void addMapButtonsListener(ActionListener al) {
		btnMrXRevealLocation.addActionListener(al);
		btnSave.addActionListener(al);
		btnMain.addActionListener(al);
		btnLoad.addActionListener(al);
		btnExit.addActionListener(al);
		tglMute.addActionListener(al);
	}

	/**
	 * Updates the travel log each time MrX makes a move. Gets the object by its
	 * name
	 * 
	 * @param ticket
	 *            Ticket used
	 * @param round
	 *            The round it was used
	 */
	public void setTravelLogIcon(Ticket ticket, int round) {
		String field = "iconRound" + round;
		JLabel lblIcon;
		ImageIcon icon;
		try {
			lblIcon = (JLabel) getClass().getDeclaredField(field).get(this);
			switch (ticket) {
			case Taxi:
				icon = new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/taxi16.png"));
				break;
			case Bus:
				icon = new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/bus.png"));
				break;
			case Underground:
				icon = new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/underground32.png"));
				break;
			case SecretMove:
				icon = new ImageIcon(ScotlandYardMapGUI.class.getResource("/solution/resources/secretMove20.png"));
				break;
			default:
				return;
			}
			lblIcon.setIcon(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Change ticket number of a specific player. Gets the relevant label object
	 * by its name
	 * 
	 * @param colour
	 *            The colour of the player
	 * @param ticket
	 *            The ticket to change
	 * @param num
	 *            The number to change the ticket to
	 */
	public void setTicket(Colour colour, Ticket ticket, int num) {
		if (colour != Colour.Black && !(ticket == Ticket.DoubleMove || ticket == Ticket.SecretMove)
				|| colour == Colour.Black && (ticket == Ticket.DoubleMove || ticket == Ticket.SecretMove)) {
			String field = "lbl" + colour + ticket;
			JLabel lblField;
			try {
				lblField = (JLabel) getClass().getDeclaredField(field).get(this);
				lblField.setText("" + num);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Change the name of a player. Gets the relevant label object by its name
	 * 
	 * @param colour
	 *            The colour of the player
	 * @param name
	 *            The name of the player
	 */
	public void setName(Colour colour, String name) {
		if (colour != Colour.Black) {
			String field = "lbl" + colour + "Name";
			JLabel lblField;
			try {
				lblField = (JLabel) getClass().getDeclaredField(field).get(this);
				lblField.setText(name);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param round
	 *            the round to set
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * Toggles reveal button text between "Reveal" and "Hide"
	 */
	public void toggleRevealButtonText() {
		if (btnMrXRevealLocation.getText().equals("Reveal"))
			btnMrXRevealLocation.setText(" Hide ");
		else
			btnMrXRevealLocation.setText("Reveal");
	}

	/**
	 * Resets reveal button text back to "Reveal"
	 */
	public void resetButtonText() {
		btnMrXRevealLocation.setText("Reveal");
	}

	/**
	 * Enables reveal button
	 */
	public void enableRevealButton() {
		btnMrXRevealLocation.setEnabled(true);
	}

	/**
	 * Disables reveal button
	 */
	public void disableRevealButton() {
		btnMrXRevealLocation.setEnabled(false);
	}

	/**
	 * Sets MrX last location in view If 0, then MrX's location is unknown
	 * 
	 * @param location
	 */
	public void setMrXLocation(int location) {
		if (location == 0) {
			lblLastNum.setText("Unknown");
			return;
		}
		lblLastNum.setText("" + location);
	}

	/**
	 * Set all tickets of specific player
	 * 
	 * @param colour
	 *            The colour of the player
	 * @param tickets
	 *            A map representing how many tickets of each type the player
	 *            has
	 */
	public void setTicket(Colour colour, Map<Ticket, Integer> tickets) {
		for (Map.Entry<Ticket, Integer> ticket : tickets.entrySet()) {
			setTicket(colour, ticket.getKey(), ticket.getValue());
		}
	}

	/**
	 * Displays the move selection move popup menu in the specified X and Y
	 * coordinate
	 * 
	 * @param movesForClick
	 *            The possible moves to be shown in the menu
	 * @param controller
	 *            A reference to the controller
	 * @param X
	 *            X coordinate
	 * @param Y
	 *            Y coordinate
	 */
	public void showPopupMenu(List<Move> movesForClick, ScotlandYardController controller, int X, int Y) {
		movePop = new MovePopUp(movesForClick, controller);
		movePop.show(mapPanel, X, Y);
	}

	/**
	 * Used by controller to add Mouse Motion Listener and Mouse Listener to the
	 * mapPanel
	 * 
	 * @param al
	 *            instance of MouseAdapter
	 */
	public void addMapListener(MouseAdapter al) {
		mapPanel.addMouseListener(al);
		mapPanel.addMouseMotionListener(al);
	}

	/**
	 * Resets the location of a player to null to make him disappear Used by the
	 * controller for MrX
	 * 
	 * @param colour
	 */
	public void resetLocationToNull(Colour colour) {
		knownLocations.put(colour, null);
	}

	/**
	 * Updates X and Y coordinates of a player
	 * 
	 * @param colour
	 *            The colour of the player
	 * @param x
	 *            X-Coordinate
	 * @param y
	 *            Y-Coordinate
	 */
	public void updateLocations(Colour colour, int x, int y) {
		List<Integer> change = knownLocations.get(colour);
		if (change == null) {
			change = new ArrayList<Integer>();
			change.add(x);
			change.add(y);
		} else {
			change.set(0, x);
			change.set(1, y);
		}
		knownLocations.put(colour, change);
	}

}
