import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionFile {
    public static void main(String[] args) {
        try{
            readFile("C:\\Users\\kirut\\Downloads\\Countries.txt");
        } catch (FileMissingException  e) {
            System.err.println(e.getMessage());
        }
    }

    private static void readFile(String file) throws FileMissingException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine())!=null){
                try{
                    int n = Integer.parseInt(line);
                    System.out.println("Read number"+n);
                } catch (NumberFormatException e) {
                   //throw new FileMissingException("Invalid number format"+line);
                    line="";
                }

            }
        } catch (FileNotFoundException e) {
            throw new FileMissingException("File Missing"+file);
        }catch(IOException e){
            System.out.println("IO error"+e.getMessage());
        }
    }
}

class  FileMissingException extends Exception{
    public FileMissingException(String message){
        super(message);
    }

}

class  InvalidNumberFormatException extends Exception{
    public InvalidNumberFormatException(String message){
        super(message);
    }

}
