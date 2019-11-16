package com.gojek.FileManipulation;

import java.io.File;

public interface FileManipulation {

  public String getFileExtension(File file);
  public void readTxtFile(String fileName);

}
