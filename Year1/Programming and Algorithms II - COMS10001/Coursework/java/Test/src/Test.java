import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class Test implements WindowFocusListener{
	private Drawing drawing;

	public static void main(String[] args) {
		Test test = new Test();
		test.run();
		test.animate();
	}

	void animate() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			if (drawing != null)
				SwingUtilities.invokeLater(drawing::tick);
		}
	}
	
	
	private void run() {
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawing = new Drawing();
		w.addWindowFocusListener(this);
		w.add(drawing);
		w.pack();
		w.setLocationByPlatform(true);
		w.setVisible(true);
	}

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		System.out.println("Gained focus");
		
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		System.out.println("Lost focus");
	}


}
