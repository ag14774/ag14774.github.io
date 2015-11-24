/* Display the current state of the grid on screen.  Catch mouse clicks,
convert to grid coordinates, and queue them up for the main program. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

class Display extends JPanel implements MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4305347764709463812L;
	private char[][] cells;
    private BlockingQueue<Integer> queue;

    Display(BlockingQueue<Integer> q) {
        cells = new char[3][3];
        queue = q;
        setPreferredSize(new Dimension(300,300));
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    void update(int x, int y, char c) {
    	System.out.println(cells[0][0]);
    	System.out.println(c);
        cells[x][y] = c;
        repaint();
    }

    public void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.drawLine(100, 10, 100, 290);
        g.drawLine(200, 10, 200, 290);
        g.drawLine(10, 100, 290, 100);
        g.drawLine(10, 200, 290, 200);
        g.setStroke(new BasicStroke(3));
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                char c = cells[x][y];
                if (c == 'O') nought(g, 100*x, 100*y);
                else if (c == 'X') cross(g, 100*x, 100*y);
            }
        }
    }

    void nought(Graphics2D g, int x, int y) {
        g.drawOval(12+x, 12+y, 75, 75);
    }

    void cross(Graphics2D g, int x, int y) {
        g.drawLine(12+x, 12+y, 88+x, 88+y);
        g.drawLine(12+x, 88+y, 88+x, 12+y);
    }

    public void mouseReleased(MouseEvent e) {
    	System.out.println("ggfd");
        queue.add(e.getX() / 100);
        queue.add(e.getY() / 100);
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
