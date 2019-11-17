package com.gojek.automation;

import com.gojek.utils.FileManipulationInterface;
import com.gojek.utils.FileManipulationUtil;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class.
 * @author siju
 *
 */
public class APIResponseCheckerTest {
  /**
   * Test which to compare the json responses from two different files.
   * Accepts file name from the user arguments.
   * @param file1
   * @param file2
   * @throws Exception
   */
  @Parameters({ "file1", "file2" })
  @Test(groups={"apitest"})
  public void test1(String file1, String file2) throws Exception {
    List<String> output = new ArrayList<String>();
    FileManipulationInterface fileManipulationInterfaceObject = new FileManipulationUtil();
    output = fileManipulationInterfaceObject.IComparator(file1, file2);
    System.out.println(output);
    String file = this.getClass().getClassLoader().getResource("ExpectedOutput.json").getPath();
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
    String json = org.apache.commons.io.IOUtils.toString(reader);
    ExpectedOutput input = new Gson().fromJson(json, ExpectedOutput.class);
    for(int i=0;i<output.size();i++) {
      Assert.assertEquals(output.get(i), input.getExpectedResults().get(i), "Responses does not match");
    }
  }
  /**
   * Test which to compare the json responses from two different files with thousands of records.
   * Accepts file name from the user arguments.
   * @param file1
   * @param file2
   * @throws Exception
   */
  @Parameters({ "file1", "file2" })
  @Test(groups= {"apitest"})
  public void TestThousandRecords(String file1, String file2) throws Exception {
    List<String> output = new ArrayList<String>();
    FileManipulationInterface fileManipulationInterfaceObject = new FileManipulationUtil();
    output=fileManipulationInterfaceObject.IComparator(file1,file2);
    for(int i=0;i<output.size();i++) {
      System.out.print(output.get(i));
    }
  }

}
