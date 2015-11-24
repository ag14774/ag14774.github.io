/* Enable two human players to play noughts and crosses.  The program
concentrates on graphics and concurrency, not game logic. */

import java.util.concurrent.*;

class Cross {
	private Grid grid;
	private BlockingQueue<Integer> queue;

	public static void main(String[] args) {
		Cross program = new Cross();
		program.run();
	}

	void run() {
		queue = new LinkedBlockingQueue<Integer>();
		grid = new Grid(queue);
		while (true) {
			int x = get();
			int y = get();
			grid.move(x, y);
		}
	}

	int get() {
		try {
			return queue.take();
		} catch (Exception err) {
			return -1;
		}
	}
}
