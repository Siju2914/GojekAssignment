package com.gojek.automation;

import com.gojek.exceptions.UnsupportedFileTypeException;
import com.gojek.utils.APIUtil;
import com.gojek.utils.APIUtilInterface;
import com.gojek.utils.FileManipulationUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gojek.utils.FileManipulationInterface;
import java.io.IOException;
import java.util.List;


public class APIResponseCheckerTest {

  @Parameters({ "file1", "file2" })
  @Test(groups={"apitest"})
  public void test1(String file1, String file2) throws Exception {
    FileManipulationInterface fileManipulationObject = new FileManipulationUtil();
    APIUtilInterface apiUtilObject = new APIUtil();
    List<String> valuesFromFile1 = fileManipulationObject.readTxtFile(file1);
    List<String> valuesFromFile2 = fileManipulationObject.readTxtFile(file2);
    String restAPI = apiUtilObject.getRestAPI(valuesFromFile1.get(1));
    System.out.print("response   "+restAPI);
    /*System.out.print(valuesFromFile1);
    System.out.print(valuesFromFile2);*/

  }

}
