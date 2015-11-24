public class Grade {

	public static void main(String[] args) {
		try {
			float mark = 0;
			int numOfUnits = args.length, j = 0, index = 0;
			int[] gradeBoundaries = { 0, 40, 50, 60, 70, 80, 90, 100, 101 };
			String[] grades = { "Fail", "Third Class", "Lower Second Class", "Upper Second Class", "First Class",
					"Above and Beyond", "Publishable", "Perfect" };

			if (args[0].equals("-binary") || args[0].equals("-gpa")) {
				if (args.length < 2)
					throw new ArrayIndexOutOfBoundsException();
				numOfUnits--; // if there's a flag, we have one less unit.
				j++; // And we need to ignore the first argument because it is
						// the flag
			}

			NumberInRange[] units = new NumberInRange[numOfUnits];
			for (int i = 0; i < numOfUnits; i++) {
				units[i] = new NumberInRange(new StringNum(args[i + j]), 0, 100);
				mark += units[i].getMark() * (1.0f / numOfUnits);
			}

			if (args[0].equals("-binary")) {
				index = binaryFlag(gradeBoundaries, mark);
				System.out.println(grades[index]);
			} else if (args[0].equals("-gpa"))
				System.out.println("GPA " + gpaFlag(mark));
			else {
				index = noFlag(gradeBoundaries, mark);
				System.out.println(grades[index]);
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid Grade");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Not Enough Arguments!");
			System.exit(1);
		}
	}

	private static int binaryFlag(int[] gradeBoundaries, float mark) {
		mark = (float) Math.round(mark);
		int lo = 0;
		int hi = gradeBoundaries.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			System.out.println("Split: " + mid);
			if (mark >= gradeBoundaries[mid] && mark < gradeBoundaries[mid + 1])
				return mid;
			else if (mark >= gradeBoundaries[mid + 1])
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}

	private static int noFlag(int[] gradeBoundaries, float mark) {
		mark = (float) Math.round(mark);
		if (mark < gradeBoundaries[1])
			return 0;
		else if (mark < gradeBoundaries[2])
			return 1;
		else if (mark < gradeBoundaries[3])
			return 2;
		else if (mark < gradeBoundaries[4])
			return 3;
		else if (mark < gradeBoundaries[5])
			return 4;
		else if (mark < gradeBoundaries[6])
			return 5;
		else if (mark < gradeBoundaries[7])
			return 6;
		else
			return 7;
	}

	private static float gpaFlag(float mark) {
		return (float) Math.round(mark / 20 * 10) / 10;
	}

}

class StringNum {
	private String s;

	StringNum(String s) {
		this.s = s;
	}

	int numOfDec() {
		if (s.indexOf(".") == -1)
			return 0;
		else {
			return s.length() - s.indexOf(".") - 1;
		}
	}

	String getString() {
		return s;
	}
}

class NumberInRange {
	private float mark;

	NumberInRange(StringNum markStr, int lowerBound, int upperBound) {
		float mark;

		if (markStr.getString().startsWith("0") && Float.parseFloat(markStr.getString()) >= 1)
			throw new IllegalArgumentException("Leading zeros detected!");
		else if (markStr.numOfDec() > 1)
			throw new IllegalArgumentException("More than one decimal places detected!");
		else
			mark = Float.parseFloat(markStr.getString());

		if (mark < lowerBound || mark > upperBound)
			throw new IllegalArgumentException("Grade out of bounds!");
		else
			this.mark = mark;
	}

	float getMark() {
		return mark;
	}
}
