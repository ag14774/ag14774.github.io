import java.io.*;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author Andreas Georgiou (ag14774)
 *
 */
public class DSAP1 {

	int n;
	boolean[][] input;
	int[][] mem;
	int max;
	
	public DSAP1(String inputFile) {
		parseAndAllocate(inputFile);
		max = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String option = args[0];
		String inputFile = args[1];
		DSAP1 run = new DSAP1(inputFile);
		switch(option) {
		case "-r":
			run.runlesR();
			break;
		case "-m":
			run.runlesM();
			break;
		case "-i":
			run.runlesI();
			break;
		default:
			System.exit(1);
			break;
		}
		run.printResult();
	}
	
	private void runlesR(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				int temp = lesR(i,j);
				max = Math.max(max, temp);
			}
		}
	}
	
	private void runlesM(){
		mem = new int[n][n];
		for(int[] row:mem)
			Arrays.fill(row,-1);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				int temp = lesM(i,j);
				max = Math.max(max, temp);
			}
		}
	}
	
	private void runlesI(){
		mem = new int[n][n];
		lesI();
	}
	
	private int lesR(int x, int y){
		boolean full = input[x][y];
		if(full)
			return 0;
		if(x==0 || y==0)
			return 1;
		return Math.min(lesR(x-1,y), Math.min(lesR(x,y-1), lesR(x-1,y-1))) + 1;
	}
	
	private int lesM(int x, int y){
		boolean full = input[x][y];
		if(full)
			return mem[x][y] = 0;
		if(x==0 || y==0)
			return mem[x][y] = 1;
		if(mem[x][y]==-1)
			mem[x][y] = Math.min(lesM(x-1,y), Math.min(lesM(x,y-1), lesM(x-1,y-1))) + 1;
		return mem[x][y];
	}
	
	private int lesI(){
		for(int x=0;x<n;x++){
			for(int y=0;y<n;y++){
				boolean full = input[x][y];
				if(full)
					mem[x][y] = 0;
				else if(x==0||y==0)
					mem[x][y] = 1;
				else
					mem[x][y] = Math.min(mem[x-1][y-1],Math.min(mem[x-1][y],mem[x][y-1])) + 1;
				max = Math.max(max,mem[x][y]);
			}
		}
		return max;
	}
	
	void parseAndAllocate(String inputFile){
		File file = new File(inputFile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			n = Integer.parseInt(reader.readLine());
			input = new boolean[n][n];
			for(int x = 0;x<n;x++){
				String line = reader.readLine();
				for(int y = 0;y<n;y++){
					char c = line.charAt(y);
					input[x][y] = c=='0' ? false : true;
				}
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void printResult(){
		System.out.println(max);
	}
	

}