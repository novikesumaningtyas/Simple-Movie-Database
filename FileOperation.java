import java.util.*;
import java.io.*;

/**
 * File Operation Class
 * For read and write to file txt
 * 
 * @author Novi Kesumaningtyas
 * @version 1 - 25 May 2017
 */
public class FileOperation
{
    /**
     * Constructor for objects of class FileOperation
     */
    public FileOperation()
    {

    }
    
    /**
     * readText(String filename)
     * Read movies details from the text given
     * and keep it in string
     * 
     * @param filename - file name which this method should read
     * 
     * @return String - contain all words from the file
     */
    public String readText(String filename)
    {
        StringBuilder texts = new StringBuilder();
        
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
     
            while (parser.hasNextLine())
            {
                String line = parser.nextLine();
                texts.append(line + ";");
            }
            
            inputFile.close();
            return texts.toString();
        }
        
        catch(FileNotFoundException exception) 
        { 
            String error = filename + " not found";
            System.out.println(error);
            return error;
        } 
        
        catch(IOException exception) 
        {
            String error = "Unexpected I/O error occured";
            System.out.println(error); 
            return error;
        }    
    }
    
    /**
     * writeToText(String filename, String moviesDetails, String lastLine)
     * Write movies details back to the text file
     * 
     * @param String filename - where the files should be written
     * @param String moviesDetails - content should be written to the file
     * @param String lastLine - the last line from movies detail should be written to the file
     */
    public void writeToText(String filename, String moviesDetails, String lastLine)
    {
        String[] texts = moviesDetails.split(";");
       
        try
        {
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            
            for(int line = 0; line < texts.length ; line++)
            {
                output.println(texts[line]);
            }
            
            output.print(lastLine);
            output.close();
            System.out.println("\t\tSuccessfully write movies list to the file");
        }
       
        catch(FileNotFoundException exception) 
        { 
            System.out.println(filename + " not found"); 
        } 
        
        catch(IOException exception) 
        {
            System.out.println("Unexpected I/O error occured"); 
        }    
    }
}
