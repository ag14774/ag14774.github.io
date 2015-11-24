import java.util.Set;
import java.util.HashSet;

/**
 * Class that represents a graph. This class is based around a List of nodes and
 * a List of edges. The nodes are very simple classes that only contain the name
 * of the node. The edges are more important as they define the structure of the
 * graph.
 */
public class Graph {
	private Set<Node> nodes;
	private Set<Edge> edges;
	private Group group = Group.General;

	public enum Group {
		General, Government, Commuter, CS;

		final private double GovLocal = 4500;
		final private double GovMain = 4000;
		final private double GovUnder = 1000;
		final private double GovInitial = 5000;
		final private double ComLocal = 0.2;
		final private double ComMain = 0.5;
		final private double ComUnder = 1.0;
		final private double ComInitial = 0.0;
		final private double CSLocal = 0.2;
		final private double CSMain = 0.6;
		final private double CSUnder = 0.9;
		final private double CSInitial = 0.0;

		double[] getCost() {
			double[] costs = { 0.0, 0.0, 0.0, 0.0 };
			switch (this) {
			case Government:
				costs[0] = GovLocal;
				costs[1] = GovMain;
				costs[2] = GovUnder;
				costs[3] = GovInitial;
				break;
			case Commuter:
				costs[0] = ComLocal;
				costs[1] = ComMain;
				costs[2] = ComUnder;
				costs[3] = ComInitial;
				break;
			case CS:
				costs[0] = 1 / CSLocal;
				costs[1] = 1 / CSMain;
				costs[2] = 1 / CSUnder;
				costs[3] = CSInitial;
				break;
			default:
				break;
			}
			return costs;

		}
	}

	/**
	 * Graph constructor. This initialises the lists that will hold the nodes
	 * and edges
	 */
	public Graph() {
		nodes = new HashSet<Node>();
		edges = new HashSet<Edge>();
	}

	public Graph(Graph graph) {
		nodes = new HashSet<Node>();
		edges = new HashSet<Edge>();
		for (Node node : graph.nodes())
			nodes.add(new Node(node));
		for (Edge edge : graph.edges()) {
			Node n1 = null;
			Node n2 = null;
			String id1 = edge.id1();
			String id2 = edge.id2();
			for (Node node : nodes) {
				if (node.name().equals(id1))
					n1 = node;
				else if (node.name().equals(id2))
					n2 = node;
				if (n1 != null && n2 != null) {
					edges.add(new Edge(n1, n2, edge.weight(), edge.type()));
					break;
				}
			}
		}
		// edges.add(new Edge(edge));
	}

	public Graph(Set<Node> nodes, Set<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	public void setGType(Group group) {
		this.group = group;
	}

	/**
	 * Function that returns the list of nodes from the graph
	 * 
	 * @return The list of nodes
	 */
	public Set<Node> nodes() {
		return nodes;
	}

	/**
	 * Function that returns the list of edges from the graph
	 * 
	 * @return The list of edges
	 */
	public Set<Edge> edges() {
		return edges;
	}

	/**
	 * Function to find a node in the graph given the nodes name. This function
	 * will search through the list of nodes and check each of their names. If
	 * it finds a matching node, it will be returned. If not, it will return
	 * null.
	 * 
	 * @param name
	 *            The name of the node that you wish to find
	 * @return The found node or null
	 */
	public Node find(String name) {
		for (Node n : nodes) {
			if (n.name().equals(name))
				return n;
		}
		return null;
	}

	/**
	 * Returns the number of nodes in the graph
	 * 
	 * @return The number of nodes in the graph
	 */
	public int nodeNumber() {
		return nodes.size();
	}

	/**
	 * Function to add a new node to the graph
	 * 
	 * @param node
	 *            The node you wish to add
	 */
	public void add(Node node) {
		nodes.add(node);
	}

	/**
	 * Function to add a new edge to the graph
	 * 
	 * @param edge
	 *            The edge you wish to add
	 */
	public void add(Edge edge) {
		edges.add(edge);
	}

	public double sumOfEdges() {
		double sum = 0;
		for (Edge edge : edges)
			sum += edge.weight();
		return sum;
	}

	void updateEdges() {
		double[] rates = group.getCost();
		double newWeight = 0;
		for (Edge edge : edges) {
			newWeight = 0;
			switch (edge.type()) {
			case LocalRoad:
				newWeight = edge.weight() * rates[0] + rates[3];
				break;
			case MainRoad:
				newWeight = edge.weight() * rates[1];
				break;
			case Underground:
				newWeight = edge.weight() * rates[2];
				break;
			}
			edge.setWeight(newWeight);
		}
	}
}
