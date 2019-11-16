package com.gojek.FileManipulation;

import java.io.File;


public class FileManipulationImpl implements FileManipulation {
  public String getFileExtension(File file) {
    String fileName = file.getName();
    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".")+1);
    else return "";

  }

  public void readTxtFile(String fileName) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    String fileExtension = getFileExtension(file);
    if(fileExtension!="txt"){

    }
  }
}
