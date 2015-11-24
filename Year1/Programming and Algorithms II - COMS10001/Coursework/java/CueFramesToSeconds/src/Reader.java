import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Reader {
	
	private final static String input = "cuedata.txt";
	private final static String output = "cuedata_fixed.txt";
	List<CueLine> data;
	
	public static void main(String[] args){
		Reader reader = new Reader();
		try {
			reader.readLines();
		} catch (FileNotFoundException e) {
			System.err.println("FILE NOT FOUND!");
			e.printStackTrace();
		}
		try {
			reader.writeLines();
		} catch (IOException e) {
			System.err.println("IOEXCEPTION!");
			e.printStackTrace();
		}
	}
	
	public Reader() {
		data = new ArrayList<CueLine>();
	}
	
	private void readLines() throws FileNotFoundException{
		File file = new File(input);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()){
			String line = in.nextLine();
			data.add(new CueLine(line));
		}
		in.close();
	}
	
	private void writeLines() throws IOException{
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf-8"));
		for(CueLine line : data){
			writer.write(line+"\n");
			System.out.println(line);
		}
		writer.close();
	}

}
