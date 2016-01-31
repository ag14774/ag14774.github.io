import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;


public class RLEtoPGM {
    
    String filename;
    
    public RLEtoPGM(String filename) {
        this.filename = filename;
    }

    public static void main (String args[]){
        RLEtoPGM converter = new RLEtoPGM(args[0]);
        converter.convert();
    }
    
    public void convert(){
        File file = new File(filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("output.pgm")); 
            byte first[] = {'P','5','\n'};
            writer.write(first);
            String line = reader.readLine();
            String args[] = line.split(",");
            String width = args[0].split("=")[1].trim();
            String height = args[1].split("=")[1].trim();
            char second[] = new char[width.length()+height.length()+2];
            int j = 0;
            for(int i = 0;i<width.length();i++){
                second[j] = width.charAt(i);
                j++;
            }
            second[j]=' ';j++;
            for(int i = 0;i<height.length();i++){
                second[j] = height.charAt(i);
                j++;
            }
            second[j]='\n';
            writer.write(new String(second).getBytes());
            writer.write("255\n".getBytes());
            boolean run = true;
            int pointer = 0;
            int count = 0;
            while(run){
                line = reader.readLine();
                for(int i = 0;i<line.length();i++){
                    char c = line.charAt(i);
                    if(c=='!'){
                        run = false;
                        break;
                    }
                    if(c!='b' && c!='o' && c!='$'){
                        int num = Character.getNumericValue(c);
                        count = count * 10 + num;
                    }
                    if(c=='b'){
                        if(count==0)
                            count = 1;
                        System.out.println(count +" blacks");
                        for(int l=0;l<count;l++){
                            writer.write(0x00);
                            pointer++;
                        }
                        count = 0;
                    }
                    if(c=='o'){
                        if(count==0)
                            count = 1;
                        System.out.println(count +" whites");
                        for(int l=0;l<count;l++){
                            writer.write(0xff);
                            pointer++;
                        }
                        count = 0;
                    }
                    if(c=='$'){
                        count--;
                        System.out.println(count +" lines");
                        for(int l=0;l<Integer.parseInt(width)-pointer;l++){
                            writer.write(0x00);
                        }
                        for(int l=0;l<count*Integer.parseInt(width);l++){
                            writer.write(0x00);
                        }
                        count = 0;
                        pointer = 0;
                    }
                }
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}