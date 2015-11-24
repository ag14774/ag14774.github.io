/* Hold the current state of the game. That's the contents of the 3x3 grid and
whose turn it is. */

import java.util.concurrent.*;

class Grid {
    private char X='X', O='O', S=' ';
    private char[][] cells;
    private char whoseTurn = X;
    private Window window;

    Grid(BlockingQueue<Integer> queue) {
        if (queue != null) window = new Window(queue);
        cells = new char[][] {{S,S,S},{S,S,S},{S,S,S}};
    }

    void move(int x, int y) {
        cells[x][y] = whoseTurn;
        if (window != null) window.update(x, y, whoseTurn);
        if (whoseTurn == X) whoseTurn = O;
        else whoseTurn = X;
    }

    public static void main(String[]args) {
        Grid grid = new Grid(null);
        grid.test();
    }

    void test() {
        is(cells[1][1], S);
        move(1,1);
        is(cells[1][1], X);
        is(cells[0][2], S);
        move(0,2);
        is(cells[0][2], O);
    }

    void is(Object a, Object b) {
        if (a == b) return;
        if (a != null && a.equals(b)) return;
        throw new Error("Test failed");
    }
}
