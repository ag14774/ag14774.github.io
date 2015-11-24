import java.util.TreeSet;

public class TreeSetWrapper implements Comparable<TreeSetWrapper> {
	
	private TreeSet<Node> set;

	TreeSetWrapper(TreeSet<Node> set) {
		this.set = set;
	}

	public int compareTo(TreeSetWrapper set2) {
		return Integer.valueOf(set.first().name()).compareTo(Integer.valueOf(set2.getSet().first().name()));
	}

	@Override
	public String toString() {
		String s = "";
		for (Node node : set) {
			s = s + " " + node.name();
		}
		return s;
	}
	
	public TreeSet<Node> getSet(){
		return set;
	}

}