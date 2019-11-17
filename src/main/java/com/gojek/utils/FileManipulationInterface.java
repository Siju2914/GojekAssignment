package com.gojek.utils;

import com.gojek.exceptions.UnsupportedFileTypeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Interface which has all the methods required for file manipulation.
 * @author siju
 */
public interface FileManipulationInterface {
  //Fetches the file extension from given file.
  public String getFileExtension(File file);
  //Reads the text file and returns the contents.
  public BufferedReader readTxtFile(String fileName) throws IOException, UnsupportedFileTypeException;
  //Compares data from two files.
  public List<String> IComparator(String file1, String file2) throws Exception;

}
