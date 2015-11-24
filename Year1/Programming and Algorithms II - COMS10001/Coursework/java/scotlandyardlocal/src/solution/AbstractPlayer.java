package solution;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.MovePass;
import scotlandyard.Player;
import scotlandyard.Ticket;

/**
 * 
 * Abstract class of a player that implements <i>Player</i> interface.
 */
public abstract class AbstractPlayer implements Player {

	/**
	 * Blocking queues to send valid moves to the GUI and to receive back the
	 * selected move
	 */
	protected BlockingQueue<Move> moveIn;
	protected BlockingQueue<List<Move>> validMovesOut;
	protected Colour colour;

	public AbstractPlayer(Colour colour, BlockingQueue<Move> moveIn, BlockingQueue<List<Move>> validMovesOut) {
		this.moveIn = moveIn;
		this.validMovesOut = validMovesOut;
		this.colour = colour;
	}

	@Override
	public Move notify(int location, List<Move> list) {
		Move move = null;
		if (list.size() == 1 && list.get(0) instanceof MovePass)
			return list.get(0); // If the player can only make a MovePass move,
								// immediately return control to the next player
		try {
			// Send valid moves
			validMovesOut.put(list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// Take selected move
			move = moveIn.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return move;
	}

	/**
	 * String representation of a player
	 * 
	 * @param tickets
	 * @param location
	 * @return a string representing the player
	 */
	public abstract String toString(Map<Ticket, Integer> tickets, int location);

}