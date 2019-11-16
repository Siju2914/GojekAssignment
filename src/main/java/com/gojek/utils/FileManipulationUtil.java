package com.gojek.utils;

import com.gojek.exceptions.UnsupportedFileTypeException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManipulationUtil implements FileManipulationInterface {
  ClassLoader classLoader = this.getClass().getClassLoader();
  final static Logger logger = Logger.getLogger(FileManipulationUtil.class);
  public String getFileExtension(File file) {
    String fileName = file.getName();
    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".")+1);
    else return "";

  }

  public List<String> readTxtFile(String fileName) throws IOException, UnsupportedFileTypeException {
    File inputFile = new File(classLoader.getResource(fileName).getFile());
    List<String> endURL = new ArrayList<String>();
    String fileExtension = getFileExtension(inputFile);
    BufferedReader inputStream = null;
    try{
      if (fileExtension.equals("txt")){
        inputStream = new BufferedReader(new FileReader(inputFile));

        String line;
        while ((line = inputStream.readLine()) != null) {
          endURL.add(line);
        }
      }

    }catch (Exception e){
      throw new UnsupportedFileTypeException();
    }

    return endURL;

  }
}
