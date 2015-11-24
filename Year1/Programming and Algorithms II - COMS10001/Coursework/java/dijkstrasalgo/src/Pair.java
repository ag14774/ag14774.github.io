class Pair {
	private Node n1;
	private Node n2;
	private double dist;

	public Pair(Node n1, Node n2, int dist) {
		this.n1 = n1;
		this.n2 = n2;
		this.dist = dist;
	}

	public Pair(Node n1, Node n2) {
		this.n1 = n1;
		this.n2 = n2;
	}

	public static void main(String[] args) {
		Node t1 = new Node("A");
		Node t2 = new Node("B");
		Pair p1 = new Pair(t2, t1, 5);
		Pair p2 = new Pair(t1, t2, 5);
		System.out.println(p1.equals(p2));
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
	}

	// Should add a check before typecasting using instanceof. NOTE:Eclipse
	// provides autogenerated equals and hashcode methods.

	/*
	 * @Override public boolean equals(Object o) { Pair temp = (Pair) o; boolean
	 * firstCheck = n1.equals(temp.getFirstNode()) &&
	 * n2.equals(temp.getSecondNode()); boolean secondCheck =
	 * n1.equals(temp.getSecondNode()) && n2.equals(temp.getFirstNode()); return
	 * (firstCheck || secondCheck); }
	 * 
	 * @Override public int hashCode() { return (int) (n1.hashCode() *
	 * n2.hashCode()); }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * ((n1 == null) ? 0 : n1.hashCode());
		result = result * ((n2 == null) ? 0 : n2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pair))
			return false;
		Pair other = (Pair) obj;
		if (n1 == null && n2 == null) {
			if (other.n1 != null || other.n2 != null)
				return false;
		}
		boolean firstCheck = n1.equals(other.getFirstNode()) && n2.equals(other.getSecondNode());
		boolean secondCheck = n1.equals(other.getSecondNode()) && n2.equals(other.getFirstNode());
		return (firstCheck || secondCheck);
	}

	Node getFirstNode() {
		return n1;
	}

	Node getSecondNode() {
		return n2;
	}

	double getDist() {
		return dist;
	}

	Node contains(Node node) {
		if (n1.equals(node))
			return n2;
		if (n2.equals(node))
			return n1;
		return null;
	}

}
