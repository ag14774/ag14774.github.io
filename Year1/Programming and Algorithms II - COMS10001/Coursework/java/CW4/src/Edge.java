/**
 * Class that represents a graph edge. The edges define the structure of the
 * graph as they constitute the connections between nodes.
 * 
 * In addition, they have both an edge type and an edge weight. It is important
 * that you understand this class.
 */
public class Edge {

	/**
	 * Enum type which defines the different types of edge. (similar to the
	 * different types of transport in the game 'Scotland Yard')
	 */
	public enum EdgeType {
		LocalRoad, MainRoad, Underground;
	}

	private Node id1;
	private Node id2;
	private double weight;
	private EdgeType type;

	/**
	 * Returns the id of the first node that this edge connects
	 * 
	 * @return The id of the first node
	 */
	public String id1() {
		return id1.name();
	}

	/**
	 * Returns the id of the second node that this edge connects
	 * 
	 * @return The id of the second node
	 */
	public String id2() {
		return id2.name();
	}

	/**
	 * Function to get the weight of the edge
	 * 
	 * @return The weight assigned to the edge
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Function to set the weight of the edge
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Function to get the type of the edge
	 * 
	 * @return The type of the edge
	 */
	public EdgeType type() {
		return type;
	}

	/**
	 * Class constructor. All of the information about the edge is set in this
	 * function
	 * 
	 * @param id1
	 *            The id of the first node that this edge connects
	 * @param id2
	 *            The id of the second node that this edge connects
	 * @param weight
	 *            The weight of the edge
	 * @param type
	 *            The type of the edge
	 */
	public Edge(String id1, String id2, double weight, EdgeType type) {
		this.id1 = new Node(id1);
		this.id2 = new Node(id2);
		this.weight = weight;
		this.type = type;
	}

	public Edge(Node n1, Node n2, double weight, EdgeType type) {
		this.id1 = n1;
		this.id2 = n2;
		this.weight = weight;
		this.type = type;
	}

	public Edge(Edge edge) {
		id1 = new Node(edge.id1());
		id2 = new Node(edge.id2());
		weight = edge.weight();
		type = edge.type();
	}

	@Override
	public String toString() {
		return this.id1 + " " + this.id2 + " " + this.weight + " " + this.type;
	}

	Node other(Node node) {
		if (node.equals(id1))
			return id2;
		if (node.equals(id2))
			return id1;
		return null;
	}

	boolean contains(Node node) {
		if (node.equals(id1) || node.equals(id2))
			return true;
		return false;
	}

	boolean equalNodes(Node n1, Node n2) {
		if (contains(n1) && contains(n2))
			return true;
		return false;
	}

}
