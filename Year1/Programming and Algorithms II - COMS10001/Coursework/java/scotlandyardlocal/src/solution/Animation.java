package solution;

import scotlandyard.Colour;

public class Animation implements Runnable {

	ScotlandYardMapGUI mapView;
	Colour colour;
	boolean isRunning;
	int initialX;
	int initialY;

	/**
	 * Constructor for the Animation class.
	 * 
	 * @param mapView
	 *            Reference to the map view of the game
	 * @param colour
	 *            Colour of the player to animate
	 */
	public Animation(ScotlandYardMapGUI mapView, Colour colour) {
		if (mapView.knownLocations.get(colour) != null) {
			isRunning = true;
			this.mapView = mapView;
			this.colour = colour;
			initialX = mapView.knownLocations.get(colour).get(0);
			initialY = mapView.knownLocations.get(colour).get(1);
		}
	}

	/**
	 * If the animation is running then stop and reset the player to it's
	 * original coordinates
	 */
	public void stopAnimation() {
		if (isRunning) {
			isRunning = false;
			mapView.updateLocations(colour, initialX, initialY);
		}
	}

	@Override
	public void run() {
		double i = 0;
		while (isRunning) {
			// Uses Math.sin to calculate an offset from the initialY location
			// and create the bouncing effect
			double j = Math.round(Math.sin(Math.toDegrees(i)));
			mapView.updateLocations(colour, initialX, initialY + (int) (3 * j));
			mapView.repaint();
			i += 0.01;
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
