import java.util.*;

class Graph {

	private HashSet<Node> nodes;
	private HashSet<Node> unvisitedNodes;
	private HashSet<Node> visitedNodes;
	private HashSet<Pair> neighbors;
	private Node currentNode;

	public Graph(Pair[] pairs, Node sourceNode) {
		nodes = new HashSet<Node>();
		unvisitedNodes = new HashSet<Node>();
		visitedNodes = new HashSet<Node>();
		neighbors = new HashSet<Pair>();
		for (Pair pair : pairs) {
			neighbors.add(pair);
			Node n1 = pair.getFirstNode();
			Node n2 = pair.getSecondNode();
			nodes.add(n1);
			nodes.add(n2);
			addToUnvisited(n1);
			addToUnvisited(n2);
		}
		addToVisited(sourceNode);
		removeFromUnvisited(sourceNode);
		currentNode = sourceNode;
		// Initially all nodes have infinite distance from Source
		// We check if a pair which includes source exists and update the
		// distance
		for (Pair pair : neighbors) {
			Node temp = pair.contains(sourceNode);
			if (temp != null)
				temp.setDistFromSource(pair.getDist());
		}
	}

	public static void main(String[] args) {
		Node s = new Node("s", 0);
		Node c = new Node("c");
		Node a = new Node("a");
		Node d = new Node("d");
		Node b = new Node("b");
		Pair[] pairs = { new Pair(s, c, 1), new Pair(c, a, 2), new Pair(c, d, 8), new Pair(a, b, 1), new Pair(b, d, 3) };
		Graph myGraph = new Graph(pairs, s);
		myGraph.calculate();
		myGraph.printResults();

	}

	class DistFromSourceCompare implements Comparator<Node> {
		public int compare(Node a, Node b) {
			return Double.compare(a.getDistFromSource(), b.getDistFromSource());
		}
	}

	void calculate() {
		while (!visitedNodes.equals(nodes)) {
			currentNode = findMinNode(unvisitedNodes);
			addToVisited(currentNode);
			removeFromUnvisited(currentNode);
			for (Node node : unvisitedNodes) {
				if (!neighbors.contains(new Pair(node, currentNode)))
					node.updateDistFromSource(currentNode);
				else {
					for (Pair pair : neighbors) {
						if (pair.equals(new Pair(node, currentNode))) {
							node.updateDistFromSource(pair);
							break;
						}
					}
				}
			}
		}
	}
	
	void printResults(){
		System.out.println("Distance from Source for: ");
		for(Node node:nodes){
			System.out.println(node);
		}
	}

	Node findMinNode(HashSet<Node> set) {
		DistFromSourceCompare comparator = new DistFromSourceCompare();
		return Collections.min(set, comparator);
	}

	void addToVisited(Node n) {
		visitedNodes.add(n);
	}

	void addToUnvisited(Node n) {
		unvisitedNodes.add(n);
	}

	void removeFromUnvisited(Node n) {
		unvisitedNodes.remove(n);
	}

	void removeFromVisited(Node n) {
		visitedNodes.remove(n);
	}

	void setCurrentNode(Node n) {
		currentNode = n;
	}

}
