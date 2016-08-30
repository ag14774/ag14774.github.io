import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 */

/**
 * @author Andreas Georgiou (ag14774)
 *
 */
public class DSAP3 {

	Magician root;
	int max;
	int n = 0;
	
	public DSAP3(String inputFile) {
		parseAndAllocate(inputFile);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFile = args[0];
		DSAP3 run = new DSAP3(inputFile);
		run.magicRunner();
		run.printResult();
	}
	
	void magicRunner(){
		max = magicTournament(root);
	}
	
	int magicTournament(Magician root){
		root.includingMe = root.ability;
		root.excludingMe = 0;
		Iterator<Magician> it = root.getChildrenIterator();
		while(it.hasNext()){
			Magician child = it.next();
			root.excludingMe += magicTournament(child);
			root.includingMe += child.excludingMe;
		}
		return Math.max(root.includingMe, root.excludingMe);
	}
	
	void parseAndAllocate(String inputFile){
		File file = new File(inputFile);
		try {
			MagicianFactory factory = new MagicianFactory();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int n = Integer.parseInt(reader.readLine());
			for(int i = 0;i<n;i++){
				String[] line = reader.readLine().trim().split("[:|\\s+]+");
				int id = Integer.parseInt(line[0]);
				int ability = Integer.parseInt(line[1]);
				Magician mag = factory.generateMagician(id, ability);
				if(id==1)
					root = mag;
				for(int j=2;j<line.length;j++){
					Magician child = factory.generateMagician(Integer.parseInt(line[j]),0);
					mag.addChild(child);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void printResult(){
		System.out.println(max);
	}
	
}

class Magician {
	int id;
	int ability;
	int includingMe;
	int excludingMe;
	Set<Magician> children;
	
	public Magician(int id){
		this.id = id;
	}
	
	public Magician(int id, int ability){
		this(id);
		this.ability = ability;
		includingMe = 0;
		excludingMe = 0;
		children = new HashSet<Magician>();
	}
	
	Iterator<Magician> getChildrenIterator(){
		return children.iterator();
	}
	
	void addChild(Magician m){
		children.add(m);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magician other = (Magician) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

class MagicianFactory {
	Map<Integer,Magician> allMagicians;
	
	public MagicianFactory(){
		allMagicians = new HashMap<Integer,Magician>();
	}
	
	Magician generateMagician(int id, int ability){
		Magician mag = allMagicians.get(id);
		if(mag == null){
			mag = new Magician(id,ability);
			allMagicians.put(id, mag);
		}
		if(mag.ability==0)
			mag.ability=ability;
		return mag;
	}
	
}