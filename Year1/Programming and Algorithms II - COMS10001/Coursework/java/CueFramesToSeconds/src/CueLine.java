
public class CueLine {

	int minutes;
	int seconds;
	int fractions;
	
	public CueLine(String line) {
		String[] splitted = line.split(":|\\.");
		minutes = Integer.parseInt(splitted[0]);
		seconds = Integer.parseInt(splitted[1]);
		int frames = Integer.parseInt(splitted[2]);
		fractions=frames2fractions(frames);
	}

	int frames2fractions(int frames){
		return Math.round((frames / 75.0f) * 1000);
	}
	
	@Override
	public String toString(){
		return minutes+":"
				+String.format("%02d",seconds)+"."
				+String.format("%03d", fractions);
	}
}
