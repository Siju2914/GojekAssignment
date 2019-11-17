package com.gojek.exceptions;

/**
 * Custom Exception for other than text file exception.
 */
public class UnsupportedFileTypeException extends Exception{
  public UnsupportedFileTypeException(String s){
    super(s);
  }

  public UnsupportedFileTypeException() {
    System.out.print("This file type is not supported");
  }
}
