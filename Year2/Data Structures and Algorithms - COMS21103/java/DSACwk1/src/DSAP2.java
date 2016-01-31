import java.io.*;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author Andreas Georgiou (ag14774)
 *
 */
public class DSAP2 {

	int n;
	int m;
	int[][] input;
	int[][] mem;
	int max;
	
	public DSAP2(String inputFile) {
		parseAndAllocate(inputFile);
		max = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String option = args[0];
		String inputFile = args[1];
		DSAP2 run = new DSAP2(inputFile);
		switch(option) {
		case "-r":
			run.runR();
			break;
		case "-m":
			run.runM();
			break;
		case "-i":
			run.runI();
			break;
		default:
			System.exit(1);
			break;
		}
		run.printResult();
	}
	
	void runR(){
		max = Math.max(cornershopR(n-1,0), cornershopR(n-1,1));
	}
	
	void runM(){
		mem = new int[2][n];
		Arrays.fill(mem[0],-1);
		Arrays.fill(mem[1],-1);
		max = Math.max(cornershopM(n-1,0), cornershopM(n-1,1));
	}
	
	void runI(){
		mem = new int[2][n];
		cornershopI();
		max = Math.max(mem[0][n-1], mem[1][n-1]);
	}
	
	int cornershopR(int i, int x){
		if(i==0)
			return input[x][i];
		return Math.max(cornershopR(i-1,x), cornershopR(i-1,x^1)-m) + input[x][i];
	}
	
	int cornershopM(int i, int x){
		if(i==0)
			return input[x][i];
		if(mem[x][i]==-1)
			mem[x][i] = Math.max(cornershopM(i-1,x), cornershopM(i-1,x^1)-m) + input[x][i];
		return mem[x][i];
	}
	
	void cornershopI(){
		for(int i=0;i<n;i++){
			for(int j=0;j<2;j++){
				if(i==0)
					mem[j][i] = input[j][i];
				else
					mem[j][i] = Math.max(mem[j][i-1], mem[j^1][i-1]-m) + input[j][i];
			}
		}
	}
	
	void parseAndAllocate(String inputFile){
		File file = new File(inputFile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String[] args = line.trim().split(" +");
			n = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			input = new int[2][n];
			for(int x = 0;x<2;x++){
				line = reader.readLine();
				args = line.trim().split(" +");
				for(int y = 0;y<n;y++){
					input[x][y] = Integer.parseInt(args[y]);
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