import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.*;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4778239491238916472L;
	private double angle = 0.0;
	private boolean clockwise = false;
	private Image back;
	private int n = 0;

	public Drawing() {
		setPreferredSize(new Dimension(400, 300));
	}

	void tick() {
		if (clockwise)
			angle += 0.1;
		else
			angle -= 0.1;
		if (angle >= 0.4)
			clockwise = false;
		else if (angle <= -0.4)
			clockwise = true;
		repaint();
	}

	void count() {
		n++;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		g.drawImage(back, 0, 0, null);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		g.drawString("" + n, 10, 10);
		g.rotate(angle, 200, 150);
		g.fillRect(50, 115, 300, 10);
		g.fillOval(175, 125, 50, 50);
	}

}
