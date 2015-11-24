import java.util.*;

public class Combination {

	private static Set<TreeSet<Node>> numOfCliques;

	public static Set<TreeSet<Node>> combination(Node[] elements, int K) {
		numOfCliques=new HashSet<TreeSet<Node>>();
		int N = elements.length;
		if (K > N)
			return numOfCliques;

		// get the combination by index
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[K]; // Stores the positions of the elements
										// of the subset of length K

		int r = 0; // r=>position in combination array
		int index = 0; // index=>position of element ready to be stored in
						// current r

		while (r >= 0) {
			if (index <= (N + (r - K))) {
				combination[r] = index;
				if (r == K - 1) {
					execute(combination, elements);
					index++;
				} else {
					index = combination[r] + 1;
					r++;
				}
			} else {
				r--;
				if (r > 0)
					index = combination[r] + 1;
				else
					index = combination[0] + 1;
			}
		}
		return numOfCliques;
	}

	public static void execute(int[] combination, Node[] elements) {

		TreeSet<Node> output = new TreeSet<Node>();
		for (int z = 0; z < combination.length; z++) {
			output.add(elements[combination[z]]);
		}
		if (Node.isFullyConnected(output))
			numOfCliques.add(output);
	}
}