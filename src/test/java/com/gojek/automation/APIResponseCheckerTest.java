package com.gojek.automation;

import com.gojek.FileManipulation.FileManipulationImpl;
import com.gojek.responsecheck.APIResponseChecker;
import org.testng.annotations.Test;
import com.gojek.FileManipulation.FileManipulation;

import java.io.File;
import java.io.IOException;


public class APIResponseCheckerTest {

  @Test(groups={"apitest"})
  public void test1() throws IOException {
    APIResponseChecker apiResponseChecker = new APIResponseChecker();
    String response = apiResponseChecker.getRestAPI("https://reqres.in/api/users/2");
    System.out.print("Response : "+response);
     ClassLoader classLoader = this.getClass().getClassLoader();
    File file = new File(classLoader.getResource("file1.txt").getFile());
    FileManipulation obj = new FileManipulationImpl();
    System.out.print(obj.getFileExtension(file));

  }

}
