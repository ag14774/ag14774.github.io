import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SpanningTree {

	private Graph graph;

	public SpanningTree(String filename) {
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
					mode = Mode.p2;
					break;
				} else if (arg.equals("-p3")) {
					mode = Mode.p3;
					break;
				} else
					throw new IllegalArgumentException("Wrong Arguments provided!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.err.println(e.getMessage());
			System.exit(1);
		}

		SpanningTree spanningTree = new SpanningTree(file);
		switch (mode) {
		case p1:
			spanningTree.partOne();
			break;
		case p2:
			spanningTree.partTwo(file);
			break;
		case p3:
			spanningTree.partThree();
			break;
		default:
			System.err.println("Something bad happened!");
			System.exit(1);
			break;
		}

	}

	void partOne() {
		double result = graph.sumOfEdges();
		result *= 1000;
		result = (double) Math.round(result * 100) / 100;
		System.out.print("Total Cable Needed: ");
		System.out.println(result + "m");
	}

	void partTwo(String file) {
		Graph govGraph, comGraph, csGraph;
		double govCost = 0;
		double comHours = 0;
		double csTime = 0;
		SimpleDateFormat df = new SimpleDateFormat("EEE d MMMM YYYY HH:mm");

		govGraph = new Graph(graph);
		comGraph = new Graph(graph);
		csGraph = new Graph(graph);

		govGraph.setGType(Graph.Group.Government);
		comGraph.setGType(Graph.Group.Commuter);
		csGraph.setGType(Graph.Group.CS);

		govGraph.updateEdges();
		comGraph.updateEdges();
		csGraph.updateEdges();

		govCost = govGraph.sumOfEdges();
		comHours = comGraph.sumOfEdges();
		csTime = csGraph.sumOfEdges() * 24 * 60 * 60;

		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 15, 0, 0, 0);
		cal.add(Calendar.SECOND, (int) (csTime));
		String cssTime = df.format(cal.getTime());

		System.out.printf("Price: %.2f\n", govCost);
		System.out.printf("Hours of Disrupted Travel: %.2fh\n", comHours);
		System.out.printf("Completion Date: %s\n", cssTime);
	}

	void partThree() {
		Graph govGraph, comGraph, csGraph;
		double govCost = 0;
		double comHours = 0;
		double csTime = 0;
		SimpleDateFormat df = new SimpleDateFormat("EEE d MMMM YYYY HH:mm");

		govGraph = new Graph(graph);
		comGraph = new Graph(graph);
		csGraph = new Graph(graph);

		govGraph.setGType(Graph.Group.Government);
		comGraph.setGType(Graph.Group.Commuter);
		csGraph.setGType(Graph.Group.CS);

		govGraph.updateEdges();
		comGraph.updateEdges();
		csGraph.updateEdges();

		govGraph = Prim.calcPrim(govGraph);
		comGraph = Prim.calcPrim(comGraph);
		csGraph = Prim.calcPrim(csGraph);

		// Uncomment to create files for each graph
		/*
		 * try { Writer.write("gov.txt", govGraph); Writer.write("com.txt",
		 * comGraph); Writer.write("cs.txt", csGraph); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		govCost = govGraph.sumOfEdges();
		comHours = comGraph.sumOfEdges();
		csTime = csGraph.sumOfEdges() * 24 * 60 * 60;

		Calendar cal = Calendar.getInstance();
		cal.set(2014, 1, 15, 0, 0, 0);
		cal.add(Calendar.SECOND, (int) (csTime));
		String cssTime = df.format(cal.getTime());

		System.out.printf("Price: %.2f\n", govCost);
		System.out.printf("Hours of Disrupted Travel: %.2fh\n", comHours);
		System.out.printf("Completion Date: %s\n", cssTime);
	}

}

enum Mode {
	p1, p2, p3;
}
