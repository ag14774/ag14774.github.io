package solution;

import scotlandyard.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 
 * Class that represent the popup menu with all the possible moves a player has
 */
public class MovePopUp extends JPopupMenu {

	// Paths to resources
	private static final long serialVersionUID = 3246719073063707908L;
	private static final String busSoundFile = "resources/busSound.wav";
	private static final String taxiSoundFile = "resources/taxiSound.wav";
	private static final String secretSoundFile = "resources/secretMoveSound.wav";
	private static final String undergroundSoundFile = "resources/underground.wav";
	private int choice = -1;
	ScotlandYardController controller;
	private List<Move> moves;

	/**
	 * Constructor for the pop up menu
	 * 
	 * @param moves
	 *            Possible moves to display
	 * @param controller
	 *            Reference to the controller
	 */
	public MovePopUp(List<Move> moves, ScotlandYardController controller) {
		this.controller = controller;
		this.moves = moves;
		MoveSelectionListener l = new MoveSelectionListener();
		for (int i = 0; i < moves.size(); i++) {
			Move move = moves.get(i);
			if (move instanceof MoveTicket) {
				JMenuItem item = new JMenuItem(((MoveTicket) move).toString());
				item.setActionCommand("" + i);
				item.addActionListener(l);
				add(item);
			} else if (move instanceof MoveDouble) {
				JMenuItem item = new JMenuItem(((MoveDouble) move).toString());
				item.setActionCommand("" + i);
				item.addActionListener(l);
				add(item);
			}
		}
	}

	/**
	 * 
	 * Listener that sends the selected item back to the controller
	 */
	class MoveSelectionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			URL busSound = this.getClass().getResource(busSoundFile);
			URL taxiSound = this.getClass().getResource(taxiSoundFile);
			URL secretSound = this.getClass().getResource(secretSoundFile);
			URL undergroundSound = this.getClass().getResource(undergroundSoundFile);
			choice = Integer.parseInt(e.getActionCommand());

			Move m0 = moves.get(choice);
			if (m0 instanceof MoveTicket) {
				MoveTicket m1 = (MoveTicket) m0;
				switch (m1.ticket) {
				case Taxi:
					Utilities.SoundUtilities.playSound(taxiSound);
					break;
				case Bus:
					Utilities.SoundUtilities.playSound(busSound);
					break;
				case DoubleMove:
					break;
				case SecretMove:
					Utilities.SoundUtilities.playSound(secretSound);
					break;
				case Underground:
					Utilities.SoundUtilities.playSound(undergroundSound);
					break;
				default:
					break;
				}
			}
			controller.addToSelected(moves.get(choice));
		}

	}

}
