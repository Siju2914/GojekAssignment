

 Overview
 -------------------------------------------------------------------------------------------------------
 Compares the api responses by reading the api urls from user provided files. The system accepts file names from the
 user as the runtime arguments, reads the file and hit the url for the responses. Does the same for second file as well.
  It then compares the response json for these two urls and prints the output.


 Pre-requisites
 -------------------------------------------------------------------------------------------------------
 1. Files are created under src/main/resources folder
 2. User provides the file names as argument while running the program. Example : mvn clean test -Dfile1=file1.txt
 -Dfile2=file2.txt


  How to run
 -------------------------------------------------------------------------------------------------------

  1.) Clone the repository from git url : https://github.com/Siju2914/GojekAssignment.git
  2.) Go to the project folder and run mvn clean
  3.) Run using mvn clean test -Dfile1=file1.txt -Dfile2=file2.txt for the first test
  4.) For test with thousands of records, mvn clean test -Dfile1=file3.txt -Dfile2=file4.txt



