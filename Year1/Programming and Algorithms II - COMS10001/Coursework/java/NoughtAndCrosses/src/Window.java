/* Provide a top-level window for the game. */

import javax.swing.*;

import java.util.concurrent.*;

class Window implements Runnable {
    private int task, START=0, UPDATE=1;
    private Display drawing;
    private BlockingQueue<Integer> queue;
    private int x, y;
    private char c;
    
    Window(BlockingQueue<Integer> q) {
        queue = q;
        task = START;
        SwingUtilities.invokeLater(this);
    }

    void update(int x0, int y0, char c0) {
        x = x0;
        y = y0;
        c = c0;
        task = UPDATE;
        try { SwingUtilities.invokeAndWait(this); }
        catch (Exception err) { throw new Error(err); }
    }

    public void run() {
        if (task == START) start();
        else if (task == UPDATE) update();
    }
    
    /**
	 * @wbp.parser.entryPoint
	 */
    void start() {
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing = new Display(queue);
        w.add(drawing);
        w.pack();
        w.setLocationByPlatform(true);
        w.setVisible(true);
    }

    void update() {
        drawing.update(x, y, c);
    }
}
