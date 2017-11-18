
A. Method working process Explanation
   1. validateColumnWidth(String text, int width)
	It takes two parameters i.e. text and column width. It checks to see if the given
	column width is greater than all the words length present in the text. Helps to 
	make sure that user column width is maintained at all time and prevents code 
	failure before hands.

   2. wrapText(String text, int width)
	It also takes two parameters i.e. text and column width. It stores text in a char
	value array. Loops through each char value and prints them out while maintaining
	the given set width. It uses pointer and temporary string variable to avoid 
	individual words from being fractioned in-between two lines while text wrapping.
 
————————————————————————————————————————————————————————————————————————————————————
B. How to run normally ?

Step One - Locate TextWrapper.java file from word_wrapping_exercise folder

Step Two - Open terminal

Step Three - Copy and paste the line below to compile and run the Text Wrapper file

javac TextWrapper.java | java TextWrapper ./files/input.txt 20

————————————————————————————————————————————————————————————————————————————————————

C. How to perform testing ?

Step One - Locate TestFile.java, TestCaseInput.txt and TestCaseOutput.txt file from word_wrapping_exercise and files folder

Step Two - Open terminal

Step Three - Copy and paste the line below to compile and run the Test file

javac TestFile.java | java TestFile

————————————————————————————————————————————————————————————————————————————————————

Documented by Prem Limbu