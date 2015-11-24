/**
 * Class representing a node of the graph
 */
public class Node {
	private String name;
	private Double weightFromSource;
	private Edge relatedEdge;

	/**
	 * Node constructor
	 * 
	 * @param n
	 *            Name that will be given to the node
	 */
	Node(String name) {
		this.name = name;
		weightFromSource = Double.POSITIVE_INFINITY;
	}

	Node(String name, double weightFromSource) {
		this.name = name;
		this.weightFromSource = weightFromSource;
	}

	Node(Node node) {
		name = node.name();
		weightFromSource = node.getWeightFromSource();
	}

	/**
	 * Function that gets the name that has been assigned to the node
	 * 
	 * @return
	 */
	String name() {
		return name;
	}

	/**
	 * @return the weightFromSource
	 */
	public Double getWeightFromSource() {
		return weightFromSource;
	}

	/**
	 * @param weightFromSource
	 *            the weightFromSource to set
	 */
	public void setWeightFromSource(Double weightFromSource) {
		this.weightFromSource = weightFromSource;
	}

	/**
	 * @return the relatedEdge
	 */
	public Edge getRelatedEdge() {
		return relatedEdge;
	}

	/**
	 * @param relatedEdge
	 *            the relatedEdge to set
	 */
	public void setRelatedEdge(Edge relatedEdge) {
		this.relatedEdge = relatedEdge;
	}

	void updateWeight(Edge e) {
		setWeightFromSource(Double.min(getWeightFromSource(), e.weight()));
		if (getWeightFromSource() == e.weight())
			setRelatedEdge(e);
	}

	@Override
	public String toString() {
		return name;
	}

	/*
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

	/*
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