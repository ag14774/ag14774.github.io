import java.io.IOException;
import java.util.*;

public class GraphSearch {

	private Graph graph;

	public GraphSearch(String filename) {
		try {
			Reader reader = new Reader();
			reader.read(filename);
			graph = reader.graph();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Check if the filename is correct!");
			System.exit(1);
		}

	}

	public static void main(String[] args) {
		int i = 0;
		String file = "graph.txt";
		int lastArg = 0;
		Mode mode = Mode.p1;

		try {
			while (i < args.length) {
				String arg = "-p1";

				if (args[i].startsWith("-")) {
					arg = args[i++];
					file = args[i++];
				}
				if (arg.equals("-p1")) {
					mode = Mode.p1;
					break;
				} else if (arg.equals("-p2")) {
					if (i < args.length) {
						lastArg = Integer.parseInt(args[i++]);
						mode = Mode.p2;
						break;
					} else
						throw new IllegalArgumentException("Not enough arguments provided!");
				} else if (arg.equals("-p3")) {
					mode = Mode.p3;
					break;
				} else if (arg.equals("-p4")) {
					if (i < args.length) {
						lastArg = Integer.parseInt(args[i++]);
						mode = Mode.p4;
						break;
					} else
						throw new IllegalArgumentException("Not enough arguments provided!");
				} else if (arg.equalsIgnoreCase("-p5")) {
					mode = Mode.p5;
					break;
				} else
					throw new IllegalArgumentException("Wrong Arguments provided!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.err.println(e.getMessage());
			System.exit(1);
		}

		GraphSearch graphSearch = new GraphSearch(file);
		switch (mode) {
		case p1:
			graphSearch.partOne();
			break;
		case p2:
			graphSearch.partTwo(lastArg);
			break;
		case p3:
			graphSearch.partThree();
			break;
		case p4:
			graphSearch.partFour(lastArg);
			break;
		case p5:
			graphSearch.partFive();
			break;
		default:
			System.err.println("Something bad happened!");
			System.exit(1);
			break;
		}

	}

	private void partOne() {
		graph.printNodesAndNeighbours();
	}

	private void partTwo(int n) {
		Set<Node> output = GraphSearch.neighbourSearch(graph, n);
		System.out.print("Number of nodes with at least " + n + " neighbours: ");
		System.out.println(output.size());
	}

	private void partThree() {
		Set<Node> output = GraphSearch.findFullyConnectedNeighbours(graph);
		System.out.print("Number of nodes with fully connected neighbours: ");
		System.out.println(output.size());
	}

	private void partFour(int n) {
		int result = findCliques(n).size();
		System.out.print("Number of cliques of size " + n + ": ");
		System.out.println(result);
	}

	private void partFive() {
		ArrayList<TreeSetWrapper> maximal = new ArrayList<TreeSetWrapper>();
		boolean allMaximalInSet = false;
		int n = 1;
		while (!allMaximalInSet) {
			allMaximalInSet = true;
			Set<TreeSet<Node>> currentCliques = findCliques(n++);
			for (TreeSet<Node> clique : currentCliques) {
				if (Node.moreCommonNeighbours(clique))
					allMaximalInSet = false;
				else
					maximal.add(new TreeSetWrapper(clique));
			}
		}
		if (maximal.size() != 0) {
			Collections.sort(maximal);
			System.out.println("Maximal cliques:");
			for (TreeSetWrapper clique : maximal) {
				System.out.println(clique);
			}
		} else
			System.out.println("No maximal cliques!");
	}

	private Set<TreeSet<Node>> findCliques(int n) {
		// Pruning nodes
		Set<Node> allNodes = graph.nodes();
		boolean removedSomething = true;
		while (removedSomething) {
			removedSomething = false;
			HashSet<Node> toRemove = new HashSet<Node>();
			for (Node node : allNodes) {
				if (node.degree() < n - 1) {
					removedSomething = true;
					Set<Node> neighbours = node.neighbours();
					for (Node neighbour : neighbours)
						neighbour.neighbours().remove(node);
					toRemove.add(node);
				}
			}
			for (Node node : toRemove)
				allNodes.remove(node);
		}
		// ***************
		Node[] nodesArray = new Node[allNodes.size()];
		nodesArray = allNodes.toArray(nodesArray);
		return Combination.combination(nodesArray, n);
	}

	public static Set<Node> neighbourSearch(Graph graph, int n) {
		Set<Node> output = new HashSet<Node>();
		Set<Node> nodes = graph.nodes();
		for (Node node : nodes) {
			if (node.degree() >= n)
				output.add(node);
		}
		return output;
	}

	public static Set<Node> findFullyConnectedNeighbours(Graph graph) {
		Set<Node> output = new HashSet<Node>();
		Set<Node> nodes = graph.nodes();
		for (Node node : nodes) {
			if (node.isFullyConnected())
				output.add(node);
		}
		return output;
	}

}

enum Mode {
	p1, p2, p3, p4, p5;
}
