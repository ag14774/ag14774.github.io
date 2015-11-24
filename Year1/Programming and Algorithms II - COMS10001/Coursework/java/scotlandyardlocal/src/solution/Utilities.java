package solution;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import scotlandyard.Colour;
import scotlandyard.MoveDouble;
import scotlandyard.MoveTicket;

public class Utilities {

	public static MoveTicket hideMrXMove(MoveTicket move, int lastKnownLocation) {
		if (move.colour == Colour.Black)
			return new MoveTicket(move.colour, lastKnownLocation, move.ticket);
		else
			return move;
	}

	public static MoveDouble hideMrXMove(MoveDouble move, int lastKnownLocation) {
		if (move.colour == Colour.Black) {
			if (move.moves.size() == 2) {
				MoveTicket m1 = (MoveTicket) move.moves.get(0);
				MoveTicket m2 = (MoveTicket) move.moves.get(1);
				m1 = hideMrXMove(m1, lastKnownLocation);
				m2 = hideMrXMove(m2, lastKnownLocation);
				return new MoveDouble(move.colour, m1, m2);
			}
		} else
			return move;
		return null;
	}

	/**
	 * Finds the key of a value in a map
	 *
	 * @param map
	 *            The map to search
	 * @param value
	 *            The value to search
	 * @return The first key that is mapped to the value. null if none is found
	 */
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue().equals(value))
				return entry.getKey();
		}
		return null;
	}

	/**
	 * It takes a map and checks if there are duplicate values. Note: If used to
	 * check whether a detective has caught MrX, it is essential to make sure
	 * that a detective cannot be on top of another detective at any point in
	 * time.
	 *
	 * @param map
	 *            The map to check
	 * @return True if there are duplicate values. False otherwise
	 */
	public static <K, V> boolean checkForDuplicateValues(Map<K, V> map) {
		Collection<V> valueList = map.values();
		Set<V> valueSet = new HashSet<V>(map.values());
		if (valueList.size() != valueSet.size())
			return true;
		return false;
	}

	public static int uniqueNumberGenerator(int[] possible) {
		int num = 0;
		while (num == 0) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(possible.length);
			num = possible[index];
			possible[index] = 0;
		}
		return num;
	}

	public static class SoundUtilities {

		public static void playSound(URL sound) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.start();

				// Thread.sleep(clip.getMicrosecondLength()/1000);

			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}

		}

		public static void loopSound(URL sound, int loopCounter) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.loop(loopCounter);

			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}

		}

		public static FloatControl loopSound(URL sound) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				return (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			return null;

		}
	}

}
