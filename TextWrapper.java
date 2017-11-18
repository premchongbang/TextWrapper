import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class enables a user to feed in a text file with a desired output
 * column width, which is output to the screen wrapped to the specifed output
 * column width.
 */
public class TextWrapper
{

  /**
   * Run this method to run this program.
   */
  public static void main(String[] args) throws IOException
  {
    /* this program requires 2 parameters to run */
    if (args.length >= 2)
    {
      try
      {
        // deleting existing file with same filename  
        Path fileToDeletePath = Paths.get("./files/TestCaseOutput.txt");
        Files.delete(fileToDeletePath);

        // storing system output in a log file. Will be used for testing 
        System.out.println("Output stored in TestCaseOutput.txt file");
        PrintStream console = System.out;
        File file = new File("./files/TestCaseOutput.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        TextWrapper textWrapper = new TextWrapper();
        String text = textWrapper.readText(new BufferedReader(new FileReader(args[0])));
        int colWidth = Integer.parseInt(args[1]);

        long time1 = System.nanoTime();
        textWrapper.wrapText(text, colWidth);
        //textWrapper.validateColumnWIdth(text, colWidth);
        long time2 = System.nanoTime();
        long timeTaken = time2 - time1;  
        //System.out.println("Time taken: " + timeTaken + " ns");  

        System.setOut(console);

      }
      catch (FileNotFoundException e)
      {
        System.err.println("Could not find file '" + args[0] + "'");
      }
      catch (NumberFormatException e)
      {
        System.err.println("'" + args[1] + "' is not a number");
      }
    }
    else
    {
      System.out.println("This program requires 2 parameters to run: " +
             "a filename (string) and a column width (integer)");
    }
  }

  /**
   * Reads from the BufferedReader and returns the contents as a String.
   * @param br - the BufferedReader to read from.
   * @return the text as a String.
   */
  public String readText(BufferedReader br)
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      String line;
      while ((line = br.readLine()) != null)
      {
        sb.append(line);
      }
      br.close();
    }
    catch (IOException e)
    {
      System.err.println("An IOException occurred: " + e);
    }
    return sb.toString();
  }

  /**
   * Prints the text to the screen, wrapping each line at the 'width'.
   * @param text - the text to wrap and output.
   * @param width - the column width for outputting the text.
   */

  public void wrapText(String text, int width)
  {

    // checking column width suitability with each words.
    if( validateColumnWidth(text, width) ){
      ArrayList<Character> symbol_list = new ArrayList<>();

      String symbols = " !,?.-";
      // add elements to the array list
      for (char c : symbols.toCharArray()) {
        symbol_list.add(c);
      }
      
      int pointer = 0; // keeps count of column width
      String current_str = ""; // stores the single word

      // add each char value to an array
      char[] arr = text.toCharArray();
      int arr_length = arr.length;

      for(int i = 0; i < arr_length; i++){
        pointer++;
        char c = arr[i];
        if(pointer%width == 0 && pointer != 0){
          if(i+1 < arr_length){
            char after_c = arr[i+1];
            if( symbol_list.contains(c) ){
              current_str = current_str.concat(Character.toString(c));
              System.out.println(current_str);
              current_str = "";
              pointer = 0;
            } else if(Character.isLetter(after_c) || Character.isDigit(after_c) ){
              current_str = current_str.concat(Character.toString(c));
              pointer = current_str.length();
              System.out.println();
            } else {
              current_str = current_str.concat(Character.toString(c));
              System.out.println(current_str);
              current_str = "";
              pointer = 0;
            }
          } else {
            current_str = current_str.concat(Character.toString(c));
            System.out.print(current_str);
            current_str = "";
          }
        } else {
          if( c == ' ' && pointer == 1 ){
            pointer--;
          } else if( i == (arr_length - 1) || symbol_list.contains(c) ){
            current_str = current_str.concat(Character.toString(c));
            System.out.print(current_str);
            current_str = "";
          } else {
            current_str = current_str.concat(Character.toString(c));
          }
        }
      }
   } else{
    System.out.println("Invalid column width.");
   }

    System.out.println();
  }

  /*
  * checking each word contained in the text is greater than the given column width
  */
  public boolean validateColumnWidth(String text, int width){

    // splitting text
    String[] arr = text.split("[ . , ? ! -]");

    for(String s: arr){
      if(s.length() > width){
        return false;
      }
    }

    return true;
  }

}

