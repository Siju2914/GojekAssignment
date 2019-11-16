package com.gojek.utils;

import com.gojek.exceptions.UnsupportedFileTypeException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileManipulationInterface {

  public String getFileExtension(File file);
  public List<String> readTxtFile(String fileName) throws IOException, UnsupportedFileTypeException;

}
