package solution;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.Ticket;

public class Detective extends AbstractPlayer {

	private String name;

	public Detective(Colour colour, String name, BlockingQueue<Move> moveIn, BlockingQueue<List<Move>> validMovesOut) {
		super(colour, moveIn, validMovesOut);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString(Map<Ticket, Integer> tickets, int location) {
		String s = colour + "," + location;
		for (Map.Entry<Ticket, Integer> entry : tickets.entrySet()) {
			s += "," + entry.getKey() + " " + entry.getValue();
		}
		s += "," + name;
		return s;
	}

}
