import java.io.*;
import java.util.*;

public class TestFile {
  	
	public static void main(String[] args) {
		TestFile tester =new TestFile();
		TextWrapper tw = new TextWrapper();

		try{
			// Initial setting up for test
			String column_width = "20";
			int col_width = Integer.parseInt(column_width);
			String read_file = "./files/TestCaseInput.txt";	// File path to test case input
			String write_file = "./files/TestCaseOutput.txt"; // // File path to test case output

			// invoking Text Wrapper class main method
			String[] arr_args = {read_file, column_width};
			tw.main(arr_args);

			System.out.println("\033[0m -------------------------------------------------------------------------");
			
			System.out.println("Test-Case 1");
			System.out.println("Testing Validate-Column-Width Method.");
			if( tester.TestValidateColumnWIdth() ){
				System.out.println("Success.");
			} else {
				System.out.println("\033[31m Failed.");
			}

			System.out.println("\033[0m -------------------------------------------------------------------------");
			
			System.out.println("Test-Case 2");
			System.out.println("Testing Text-Wrap Method One. Cheking each line of output file width with set column width");
			if( tester.TestWrapTextMethod(write_file, col_width) ){
				System.out.println("Success.");
			} else {
				System.out.println("\033[31m Failed.");
			}
			System.out.println("\033[0m -------------------------------------------------------------------------");
			
			System.out.println("Test-Case 3");
			System.out.println("Testing Text-Wrap Method Two. Checking number of output file lines with expected line count");
			int expected_line_count = 9; // expected number of lines in the output file
			if( tester.TestWrapTestMethod2(write_file, expected_line_count) ){
				System.out.println("Success.");
			} else {
				System.out.println("\033[31m Failed.");
			}

			System.out.println("\033[0m -------------------------------------------------------------------------");

			System.out.println("Test-Case 4");
			System.out.println("Testing word count before and after text wrapping.");
			int width = Integer.parseInt(column_width);
			if( tester.TestWordcount(read_file, write_file) ){
				System.out.println("Success.");
			} else {
				System.out.println("\033[31m Failed.");
			}

			System.out.println("\033[0m -------------------------------------------------------------------------");

		} catch (IOException e) {
        	System.err.println(e);
      	}
	}

   	public boolean TestValidateColumnWIdth(){
   		boolean check = false;
   		TextWrapper tw = new TextWrapper();

   		String str = "This is a Triometric Exercise Validation Method Test.";

   		if( tw.validateColumnWidth(str, 10) && !tw.validateColumnWidth(str, 5) ){
   			check = true;
   		}
   		return check;
   	}

   	public boolean TestWrapTextMethod(String read_file, int col_width) throws IOException{
   		boolean check = true;
		
		try {
			FileReader fileReader = new FileReader(new File(read_file));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.length() > col_width){
					check = false;
					break;
				}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return check;
   	}

   	public boolean TestWrapTestMethod2(String read_file, int exp_line_count) throws IOException {
   		boolean check = false;
   		int line_count = 0;
		
		try {
			FileReader fileReader = new FileReader(new File(read_file));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				line_count++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(line_count == exp_line_count){
			check = true;
		}

		return check;
   	}

   	public boolean TestWordcount(String read_file1, String read_file2) throws IOException {
   		boolean check = false;

		int[] arr_count = {0, 0};
		String[] store_file_name = {read_file1, read_file2};
		try {
			for(int i = 0 ; i < 2; i++){
				FileReader fileReader = new FileReader(new File(store_file_name[i]));

				BufferedReader bufferedReader = new BufferedReader(fileReader);

				StringBuffer sb = new StringBuffer();
				String line;
				int count = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if(line.length() > 0){
						char[] c = line.toCharArray();

						for(int j = 0; j < c.length; j++){
							if(Character.isLetter(c[j]) || Character.isDigit(c[j]) ){
								count++;
							}
						}
					}
				}
				arr_count[i] = count;
				count = 0;
				fileReader.close();
			}

			System.out.println("File 1 word count: " + arr_count[0] + " File 2 word count: " + arr_count[1]);

			if(arr_count[0] == arr_count[1]){
				check = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return check;
   	}
}