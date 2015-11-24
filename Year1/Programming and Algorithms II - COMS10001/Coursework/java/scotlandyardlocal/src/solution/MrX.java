package solution;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.Ticket;

public class MrX extends AbstractPlayer {

	public MrX(BlockingQueue<Move> moveIn, BlockingQueue<List<Move>> validMovesOut) {
		super(Colour.Black, moveIn, validMovesOut);
	}

	@Override
	public String toString(Map<Ticket, Integer> tickets, int location) {
		String s = colour + "," + location;
		for (Map.Entry<Ticket, Integer> entry : tickets.entrySet()) {
			s += "," + entry.getKey() + " " + entry.getValue();
		}
		return s;
	}

}
