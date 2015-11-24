class Node {

	private String name;
	private double distFromSource;

	public Node(String name) {
		this.name = name;
		this.distFromSource = Double.POSITIVE_INFINITY;
	}

	public Node(String name, double distFromSource) {
		this.name = name;
		this.distFromSource = distFromSource;
	}

	public static void main(String[] args) {
		Node n1 = new Node("A");
		Node n2 = new Node("A");
		System.out.println(n1.getDistFromSource() + 1);
		System.out.println(n1.equals(n2));
	}

	String getName() {
		return name;
	}

	double getDistFromSource() {
		return distFromSource;
	}

	public void setDistFromSource(double distFromSource) {
		this.distFromSource = distFromSource;
	}

	// Overload
	void updateDistFromSource(Pair meAndCurrent) {
		Node current = (this.equals(meAndCurrent.getFirstNode())) ? meAndCurrent.getSecondNode() : meAndCurrent
				.getFirstNode();
		distFromSource = Math.min(distFromSource, current.getDistFromSource() + meAndCurrent.getDist());
	}

	void updateDistFromSource(Node current) {
		distFromSource = Math.min(distFromSource, current.getDistFromSource() + Double.POSITIVE_INFINITY);
	}

	@Override
	public String toString() {
		return name + ": " + distFromSource;
	}

	@Override
	public boolean equals(Object o) {
		Node temp = (Node) o;
		return getName().equals(temp.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
