package solution;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.*;

import solution.ScotlandYardController.STATE;

/**
 * Entry point for Scotland Yard game
 * 
 * @author Andreas Georgiou(ag14774)
 * @author Constantinos Theophilou(ct14872)
 *
 */
public class ScotlandYardApplication {

	public static void main(String[] args) {

		//Change look and feel
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e.printStackTrace();
			}
		}

		ScotlandYardMainContainer mainContainer = new ScotlandYardMainContainer();

		new ScotlandYardController(mainContainer, null, STATE.MAIN);

	}

}