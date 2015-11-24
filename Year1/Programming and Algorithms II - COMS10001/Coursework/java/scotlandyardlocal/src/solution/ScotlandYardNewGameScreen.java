package solution;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import solution.MigLayout.MigLayout;
import scotlandyard.Colour;
import scotlandyard.Ticket;
import java.awt.Color;

//import net.miginfocom.swing.MigLayout;

public class ScotlandYardNewGameScreen extends JDialog {

	private static final long serialVersionUID = 7366686049060475791L;

	private boolean isSet;
	private Map<Colour, Map<Ticket, Integer>> tickets;
	private Map<Colour, String> names;

	private JTextField txtBlueName;
	private JTextField txtBlueTaxi;
	private JTextField txtBlueBus;
	private JTextField txtBlueUnder;

	private JTextField txtGreenName;
	private JTextField txtGreenTaxi;
	private JTextField txtGreenBus;
	private JTextField txtGreenUnder;

	private JTextField txtRedName;
	private JTextField txtRedTaxi;
	private JTextField txtRedBus;
	private JTextField txtRedUnder;

	private JTextField txtWhiteName;
	private JTextField txtWhiteTaxi;
	private JTextField txtWhiteBus;
	private JTextField txtWhiteUnder;

	private JTextField txtYellowName;
	private JTextField txtYellowTaxi;
	private JTextField txtYellowBus;
	private JTextField txtYellowUnder;
	private JPanel contentPanel;
	private JPanel btnPanel;
	private JButton btnStart;
	private JButton btnCancel;
	JCheckBox checkBlue;
	JCheckBox checkWhite;
	JCheckBox checkYellow;
	JCheckBox checkRed;
	JCheckBox checkGreen;
	private JPanel innerBtnPanel;

	public ScotlandYardNewGameScreen(Frame owner, boolean modal) {
		super(owner, modal);
		getContentPane().setBackground(Color.BLACK);

		isSet = false;
		tickets = new LinkedHashMap<Colour, Map<Ticket, Integer>>();
		names = new HashMap<Colour, String>();

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[377px]", "[194px][39px]"));

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 153, 255));
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.setLayout(new MigLayout("", "[][57.00px,grow][121.00px:n][][][]",
				"[25px][21px][21px][21px][21px][21px]"));
		getContentPane().add(contentPanel, "cell 0 0,grow");

		createLabels();

		createCheckboxes();
		
		createTextFields();

		createButtons();

		addNewGameListener(new NewGameListener());

		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Creates and adds all the buttons to the panel
	 */
	private void createButtons() {
		btnPanel = new JPanel();
		btnPanel.setBackground(new Color(0, 153, 255));
		btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnPanel.setLayout(new MigLayout("", "[365.00]", "[35px,grow]"));
		getContentPane().add(btnPanel, "cell 0 1,growx,aligny top");

		innerBtnPanel = new JPanel();
		innerBtnPanel.setBackground(new Color(0, 153, 255));
		innerBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		btnPanel.add(innerBtnPanel, "cell 0 0,alignx right,growy");

		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnStart.setActionCommand("start");
		innerBtnPanel.add(btnStart);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnCancel.setActionCommand("cancel");
		innerBtnPanel.add(btnCancel);
	}

	/**
	 * Creates and adds all the text fields to the panel
	 */
	private void createTextFields() {
		txtBlueName = new JTextField();
		contentPanel.add(txtBlueName, "cell 2 1,alignx center");
		txtBlueName.setColumns(11);

		txtBlueTaxi = new JTextField();
		txtBlueTaxi.setText("11");
		txtBlueTaxi.setHorizontalAlignment(SwingConstants.CENTER);
		txtBlueTaxi.setColumns(4);
		contentPanel.add(txtBlueTaxi, "cell 3 1,alignx center");

		txtBlueBus = new JTextField();
		txtBlueBus.setText("8");
		txtBlueBus.setHorizontalAlignment(SwingConstants.CENTER);
		txtBlueBus.setColumns(4);
		contentPanel.add(txtBlueBus, "cell 4 1,alignx center");

		txtBlueUnder = new JTextField();
		txtBlueUnder.setText("4");
		txtBlueUnder.setHorizontalAlignment(SwingConstants.CENTER);
		txtBlueUnder.setColumns(4);
		contentPanel.add(txtBlueUnder, "cell 5 1,alignx center");

		txtGreenName = new JTextField();
		txtGreenName.setColumns(11);
		contentPanel.add(txtGreenName, "cell 2 2,alignx center");

		txtGreenTaxi = new JTextField();
		txtGreenTaxi.setText("11");
		txtGreenTaxi.setHorizontalAlignment(SwingConstants.CENTER);
		txtGreenTaxi.setColumns(4);
		contentPanel.add(txtGreenTaxi, "cell 3 2,alignx center");

		txtGreenBus = new JTextField();
		txtGreenBus.setText("8");
		txtGreenBus.setHorizontalAlignment(SwingConstants.CENTER);
		txtGreenBus.setColumns(4);
		contentPanel.add(txtGreenBus, "cell 4 2,alignx center");

		txtGreenUnder = new JTextField();
		txtGreenUnder.setText("4");
		txtGreenUnder.setHorizontalAlignment(SwingConstants.CENTER);
		txtGreenUnder.setColumns(4);
		contentPanel.add(txtGreenUnder, "cell 5 2,alignx center");

		txtRedName = new JTextField();
		txtRedName.setColumns(11);
		contentPanel.add(txtRedName, "cell 2 3,alignx center");

		txtRedTaxi = new JTextField();
		txtRedTaxi.setText("11");
		txtRedTaxi.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedTaxi.setColumns(4);
		contentPanel.add(txtRedTaxi, "cell 3 3,alignx center");

		txtRedBus = new JTextField();
		txtRedBus.setText("8");
		txtRedBus.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedBus.setColumns(4);
		contentPanel.add(txtRedBus, "cell 4 3,alignx center");

		txtRedUnder = new JTextField();
		txtRedUnder.setText("4");
		txtRedUnder.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedUnder.setColumns(4);
		contentPanel.add(txtRedUnder, "cell 5 3,alignx center");

		txtWhiteName = new JTextField();
		txtWhiteName.setColumns(11);
		contentPanel.add(txtWhiteName, "cell 2 4,alignx center");

		txtWhiteTaxi = new JTextField();
		txtWhiteTaxi.setText("11");
		txtWhiteTaxi.setHorizontalAlignment(SwingConstants.CENTER);
		txtWhiteTaxi.setColumns(4);
		contentPanel.add(txtWhiteTaxi, "cell 3 4,alignx center");

		txtWhiteBus = new JTextField();
		txtWhiteBus.setText("8");
		txtWhiteBus.setHorizontalAlignment(SwingConstants.CENTER);
		txtWhiteBus.setColumns(4);
		contentPanel.add(txtWhiteBus, "cell 4 4,alignx center");

		txtWhiteUnder = new JTextField();
		txtWhiteUnder.setText("4");
		txtWhiteUnder.setHorizontalAlignment(SwingConstants.CENTER);
		txtWhiteUnder.setColumns(4);
		contentPanel.add(txtWhiteUnder, "cell 5 4,alignx center");

		txtYellowName = new JTextField();
		txtYellowName.setColumns(11);
		contentPanel.add(txtYellowName, "cell 2 5,alignx center");

		txtYellowTaxi = new JTextField();
		txtYellowTaxi.setText("11");
		txtYellowTaxi.setHorizontalAlignment(SwingConstants.CENTER);
		txtYellowTaxi.setColumns(4);
		contentPanel.add(txtYellowTaxi, "cell 3 5,alignx center");

		txtYellowBus = new JTextField();
		txtYellowBus.setText("8");
		txtYellowBus.setHorizontalAlignment(SwingConstants.CENTER);
		txtYellowBus.setColumns(4);
		contentPanel.add(txtYellowBus, "cell 4 5,alignx center");

		txtYellowUnder = new JTextField();
		txtYellowUnder.setText("4");
		txtYellowUnder.setHorizontalAlignment(SwingConstants.CENTER);
		txtYellowUnder.setColumns(4);
		contentPanel.add(txtYellowUnder, "cell 5 5,alignx center");
	}

	/**
	 * Creates and adds all the checkboxes to the panel
	 */
	private void createCheckboxes() {
		checkBlue = new JCheckBox("");
		checkBlue.setBackground(Color.GRAY);
		contentPanel.add(checkBlue, "cell 0 1");

		checkGreen = new JCheckBox("");
		checkGreen.setBackground(Color.GRAY);
		contentPanel.add(checkGreen, "cell 0 2");
		
		checkRed = new JCheckBox("");
		checkRed.setBackground(Color.GRAY);
		contentPanel.add(checkRed, "cell 0 3");
		
		checkWhite = new JCheckBox("");
		checkWhite.setBackground(Color.GRAY);
		contentPanel.add(checkWhite, "cell 0 4");
		
		checkYellow = new JCheckBox("");
		checkYellow.setBackground(Color.GRAY);
		contentPanel.add(checkYellow, "cell 0 5");
	}

	/**
	 * Creates and adds all the labels to the panel
	 */
	private void createLabels() {
		JLabel lblColour = new JLabel("Colour");
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblColour, "cell 1 0");

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblName, "cell 2 0,alignx center");

		JLabel lblTaxi = new JLabel("");
		lblTaxi.setIcon(new ImageIcon(this.getClass().getResource("/solution/resources/taxi16.png")));
		lblTaxi.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblTaxi, "cell 3 0,alignx center");

		JLabel lblBus = new JLabel("");
		lblBus.setIcon(new ImageIcon(this.getClass().getResource("/solution/resources/bus.png")));
		lblBus.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblBus, "cell 4 0,alignx center");

		JLabel lblUnderground = new JLabel("");
		lblUnderground.setIcon(new ImageIcon(this.getClass().getResource("/solution/resources/underground32.png")));
		lblUnderground.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblUnderground, "cell 5 0,alignx center");
		
		//Colour labels
		JLabel lblGreen = new JLabel("Green");
		lblGreen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(lblGreen, "flowx,cell 1 2");
		
		JLabel lblRed = new JLabel("Red");
		lblRed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(lblRed, "flowx,cell 1 3");
		
		JLabel lblWhite = new JLabel("White");
		lblWhite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(lblWhite, "flowx,cell 1 4");
		
		JLabel lblYellow = new JLabel("Yellow");
		lblYellow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(lblYellow, "flowx,cell 1 5");

		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(lblBlue, "flowx,cell 1 1");
	}

	/**
	 * @return true when all the variables are set and ready to be read by the
	 *         controller
	 */
	public boolean isSet() {
		return isSet;
	}

	/**
	 * Adds listeners to the dialog
	 * 
	 * @param al
	 */
	public void addNewGameListener(ActionListener al) {
		btnCancel.addActionListener(al);
		btnStart.addActionListener(al);
	}

	/**
	 * Adds players details to the related fields so the controller can read
	 * them
	 * 
	 * @param colour
	 * @param name
	 * @param playerTickets
	 */
	private void addPlayer(Colour colour, String name, Map<Ticket, Integer> playerTickets) {
		tickets.put(colour, playerTickets);
		names.put(colour, name);
	}

	/**
	 * Display an error
	 * 
	 * @param error
	 */
	public void displayError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}

	/**
	 * Destroy JDialog
	 */
	public void disposeWindow() {
		dispose();
	}

	/**
	 * Sets the variables of a player of a specific colour Gets the relevant
	 * text fields by their name
	 * 
	 * @param colour
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private void setVariables(Colour colour) throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		String nameField = "txt" + colour + "Name";
		String taxiField = "txt" + colour + "Taxi";
		String busField = "txt" + colour + "Bus";
		String underField = "txt" + colour + "Under";

		JTextField nameObject = (JTextField) getClass().getDeclaredField(nameField).get(this);
		JTextField taxiObject = (JTextField) getClass().getDeclaredField(taxiField).get(this);
		JTextField busObject = (JTextField) getClass().getDeclaredField(busField).get(this);
		JTextField underObject = (JTextField) getClass().getDeclaredField(underField).get(this);

		Map<Ticket, Integer> playerTickets = new HashMap<Ticket, Integer>();
		playerTickets.put(Ticket.Taxi, Integer.parseInt(taxiObject.getText()));
		playerTickets.put(Ticket.Bus, Integer.parseInt(busObject.getText()));
		playerTickets.put(Ticket.Underground, Integer.parseInt(underObject.getText()));
		playerTickets.put(Ticket.DoubleMove, 0);
		playerTickets.put(Ticket.SecretMove, 0);

		String name = nameObject.getText();
		if (name.isEmpty())
			throw new IllegalArgumentException("Name cannot be empty!");

		addPlayer(colour, name, playerTickets);
	}

	/**
	 * Sets the variables for all selected colours
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public void setVariables() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException {
		boolean atLeastOneSelected = false;
		if (checkBlue.isSelected()) {
			setVariables(Colour.Blue);
			atLeastOneSelected = true;
		}
		if (checkGreen.isSelected()) {
			setVariables(Colour.Green);
			atLeastOneSelected = true;
		}
		if (checkRed.isSelected()) {
			setVariables(Colour.Red);
			atLeastOneSelected = true;
		}
		if (checkWhite.isSelected()) {
			setVariables(Colour.White);
			atLeastOneSelected = true;
		}
		if (checkYellow.isSelected()) {
			setVariables(Colour.Yellow);
			atLeastOneSelected = true;
		}
		if (!atLeastOneSelected)
			throw new IllegalArgumentException("At least one detective must be selected!");
	}

	/**
	 * @return the tickets
	 */
	public Map<Colour, Map<Ticket, Integer>> getTickets() {
		return tickets;
	}

	/**
	 * @return the names
	 */
	public Map<Colour, String> getNames() {
		return names;
	}

	/**
	 * 
	 * Button listener for the new game configuration screen
	 *
	 */
	class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String action = arg0.getActionCommand();
			if (action.equals("start")) {
				try {
					setVariables();
					isSet = true;
					disposeWindow();
				} catch (NumberFormatException e) {
					displayError("Tickets cannot be letters!");
				} catch (IllegalArgumentException e) {
					displayError(e.getMessage());
				} catch (IllegalAccessException e) {
					displayError(e.getMessage());
				} catch (NoSuchFieldException e) {
					displayError(e.getMessage());
				} catch (SecurityException e) {
					displayError(e.getMessage());
				}
			}
			if (action.equals("cancel")) {
				disposeWindow();
			}
		}
	}

}
