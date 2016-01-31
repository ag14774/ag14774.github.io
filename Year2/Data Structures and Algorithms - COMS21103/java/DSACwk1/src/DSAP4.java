import java.io.*;

/**
 * @author Andreas Georgiou (ag14774)
 *
 */
public class DSAP4 {

	int input[][];
	int mem[][];
	boolean defined[][];
	int n;
	int max;
	
	public DSAP4(String inputFile) {
		n = 0;
		max = 0;
		parseAndAllocate(inputFile);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String option = args[0];
		String inputFile = args[1];
		DSAP4 run = new DSAP4(inputFile);
		switch(option){
		case "-r":
			run.runnerR();
			break;
		case "-m":
			run.runnerM();
			break;
		case "-i":
			run.runnerI();
			break;
		}
		run.printResult();
	}
	
	void runnerR(){
		max = dayOffR(0,0);
	}
	
	void runnerM(){
		mem = new int[n][n];
		defined = new boolean[n][n];
		max = dayOffM(0,0);
	}
	
	void runnerI(){
		mem = new int[n][n];
		max = dayOffI();
	}
	
	int dayOffR(int i, int j){
		if(i==n-1)
			return Math.min(input[0][i],input[1][j]);
		return Math.max(Math.min(input[0][i],input[1][j])+dayOffR(i+1,j+1), dayOffR(i+1,0));
	}
	
	int dayOffM(int i, int j){
		if(i==n-1)
			return Math.min(input[0][i],input[1][j]);
		if(!defined[i][j]){
			mem[i][j] = Math.max(Math.min(input[0][i],input[1][j])+dayOffM(i+1,j+1), dayOffM(i+1,0));
			defined[i][j] = true;
		}
		return mem[i][j];
	}
	
	int dayOffI(){
		for(int i=n-1;i>=0;i--){
			for(int j=0;j<=i;j++){
				if(i==n-1)
					mem[i][j] = Math.min(input[0][i],input[1][j]);
				else
					mem[i][j] = Math.max(Math.min(input[0][i],input[1][j])+mem[i+1][j+1], mem[i+1][0]);
			}
		}
		return mem[0][0];
	}
	
	void parseAndAllocate(String inputFile){
		File file = new File(inputFile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			n = Integer.parseInt(reader.readLine());
			input = new int[2][n];
			for(int i=0;i<2;i++){
				String args[] = reader.readLine().split(" +");
				for(int j=0;j<n;j++){
					input[i][j] = Integer.parseInt(args[j]);
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