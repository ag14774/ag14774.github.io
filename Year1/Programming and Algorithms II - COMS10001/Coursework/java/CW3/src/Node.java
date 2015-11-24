import java.util.*;

/**
 * Class representing a graph node. This class has a property that contains the
 * name of the node. It also contains a list of the nodes that are its
 * neighbours
 */
public class Node implements Comparable<Node> {
	private String name;
	private Set<Node> neighbours;

	/**
	 * Constructor for a node. Must pass in a string to give the node a name
	 * 
	 * @param n
	 *            The name you wish to assign to the node
	 */
	public Node(String n) {
		name = n;
		neighbours = new HashSet<Node>();
	}

	/**
	 * Function to get the name of the node
	 * 
	 * @return The name of the node
	 */
	public String name() {
		return name;
	}

	/**
	 * Function to get the list of nodes that are the node's neighbours
	 * 
	 * @return The list of the nodes neighbours
	 */
	public Set<Node> neighbours() {
		return neighbours;
	}

	/**
	 * Function to get the degree of the node (number of neighbours)
	 * 
	 * @return The degree of the node
	 */
	public int degree() {
		return neighbours.size();
	}

	/**
	 * Function to add a node to the list of neighbours
	 * 
	 * @param node
	 *            The node you wish to add
	 */
	public void addNeighbour(Node node) {
		neighbours.add(node);
	}

	public static TreeSet<Node> copyNodesToTree(Set<Node> nodes) {
		TreeSet<Node> sorted = new TreeSet<Node>(nodes);
		return sorted;
	}

	void printNeighbours() {
		if (degree() == 0)
			return;
		TreeSet<Node> sorted = Node.copyNodesToTree(neighbours());
		for (Node node : sorted)
			System.out.print(" " + node);

	}

	boolean isFullyConnected() {
		TreeSet<Node> neighbours = Node.copyNodesToTree(neighbours());
		for (Node neighbour : neighbours) {
			Set<Node> remaining = neighbours.tailSet(neighbour, false);
			for (Node it : remaining) {
				if (!neighbour.isConnected(it)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isFullyConnected(TreeSet<Node> nodes) {
		for (Node node : nodes) {
			Set<Node> remaining = nodes.tailSet(node, false);
			for (Node it : remaining) {
				if (!node.isConnected(it)) {
					return false;
				}
			}
		}
		return true;
	}
	
	Set<Node> hasCommonNeighbours(Node node){
		Set<Node> common=new HashSet<Node>(neighbours());
		common.retainAll(node.neighbours());
		return common;
	}
	
	static boolean moreCommonNeighbours(TreeSet<Node> nodes){
		TreeSet<Node> common = new TreeSet<Node>();
		Node first=nodes.first();
		Set<Node> newNodes=nodes.tailSet(first,false);
		common.addAll(first.neighbours());
		for(Node node:newNodes){
			common.retainAll(node.neighbours());
			if(common.size()==0)
				return false;
		}
		return true;
	}

	boolean isConnected(Node node) {
		if (neighbours().contains(node))
			return true;
		return false;
	}

	
	public static void main(String[] args){
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		n1.addNeighbour(n2);
		n1.addNeighbour(n3);
		n2.addNeighbour(n1);
		n2.addNeighbour(n3);
		n3.addNeighbour(n1);
		n3.addNeighbour(n2);
		TreeSet<Node> test=new TreeSet<Node>();
		test.add(n2);
		test.add(n3);
		test.add(n1);
		System.out.println(Node.moreCommonNeighbours(test));
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	// Add a check for non number
	public int compareTo(Node node) {
		try {
			return Integer.valueOf(name).compareTo(Integer.valueOf(node.name()));
		} catch (NumberFormatException e) {
			System.err.println("No parsable string detected!");
			System.exit(1);
		}
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
