import java.util.*;

public class Prim {

	private Set<Node> nodes;
	private Set<Edge> edges;
	private Set<Node> unvisitedNodes;
	private Set<Node> visitedNodes;
	private Set<Edge> visitedEdges;
	private Node currentNode;

	public Prim(Graph graph) {
		nodes = new HashSet<Node>(graph.nodes());
		edges = new HashSet<Edge>(graph.edges());
		unvisitedNodes = new HashSet<Node>(nodes);
		visitedNodes = new HashSet<Node>();
		visitedEdges = new HashSet<Edge>();
		Node sourceNode = nodes.iterator().next();
		visit(sourceNode);
		sourceNode.setWeightFromSource(0.0);
		for (Edge edge : edges) {
			Node other = edge.other(sourceNode);
			if (other != null) {
				other.setRelatedEdge(edge);
				other.setWeightFromSource(edge.weight());
			}
		}
	}

	private void visit(Node node) {
		unvisitedNodes.remove(node);
		visitedNodes.add(node);
	}

	private void visit(Edge edge) {
		visitedEdges.add(edge);
	}

	void MST() {
		while (!visitedNodes.equals(nodes)) {
			currentNode = findMin(unvisitedNodes);
			visit(currentNode);
			visit(currentNode.getRelatedEdge());
			for (Node node : unvisitedNodes) {
				Edge edge = findMinEdge(node, currentNode);
				if (edge != null)
					node.updateWeight(edge);
			}
		}
	}

	private Node findMin(Set<Node> set) {
		WeightComparator wc = new WeightComparator();
		return Collections.min(set, wc);
	}

	private Edge findMinEdge(Node n1, Node n2) {
		int i = 0;
		Set<Edge> possible = new HashSet<Edge>();
		WeightComparatorEdge wce = new WeightComparatorEdge();
		for (Edge edge : edges) {
			if (edge.equalNodes(n1, n2)) {
				i++;
				possible.add(edge);
			}
			if (i == 3)
				break;
		}
		if (possible.size() != 0)
			return Collections.min(possible, wce);
		else
			return null;
	}

	public static Graph calcPrim(Graph g) {
		Prim prim = new Prim(g);
		prim.MST();
		return new Graph(prim.nodes, prim.visitedEdges);
	}

	class WeightComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return Double.compare(n1.getWeightFromSource(), n2.getWeightFromSource());
		}
	}

	class WeightComparatorEdge implements Comparator<Edge> {
		public int compare(Edge e1, Edge e2) {
			return Double.compare(e1.weight(), e2.weight());
		}
	}

}
