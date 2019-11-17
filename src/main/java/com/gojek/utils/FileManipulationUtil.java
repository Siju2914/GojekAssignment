package com.gojek.utils;

import com.gojek.exceptions.UnsupportedFileTypeException;
import com.gojek.pojos.PaginationPojo;
import com.gojek.pojos.UserDataPojo;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonSyntaxException;

/**
 * File manipulation util that implements methods from FileManipulationInterface.
 * @author siju
 */
public class FileManipulationUtil implements FileManipulationInterface {
  ClassLoader classLoader = this.getClass().getClassLoader();
  final static Logger logger = Logger.getLogger(FileManipulationUtil.class);

  /**
   * Method to get the file extenstion.
   *
   * @param file
   * @return fileExtension
   */
  public String getFileExtension(File file) {
    String fileName = file.getName();
    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    else return "";

  }

  /**
   * Method to read the contents from a text file.
   *
   * @param fileName
   * @return response string
   * @throws IOException
   * @throws UnsupportedFileTypeException
   */
  public BufferedReader readTxtFile(String fileName) throws IOException, UnsupportedFileTypeException {
    File inputFile = new File(classLoader.getResource(fileName).getFile());
    List<String> endURL = new ArrayList<String>();
    String fileExtension = getFileExtension(inputFile);
    BufferedReader inputStream = null;
      if (fileExtension.equals("txt")) {
        inputStream = new BufferedReader(new FileReader(inputFile));
        return inputStream;
      }


    return inputStream;
  }

  /**
   * Compares the responses from two files and returns th
   *
   * @param file1
   * @param file2
   * @return
   * @throws IOException
   */
  public List<String> IComparator(String file1, String file2) throws IOException, UnsupportedFileTypeException {
    List<String> results = new ArrayList<String>();
    APIUtilInterface apiUtilInterface = new APIUtil();
    BufferedReader objReaderFile1 = null;
    BufferedReader objReaderFile2 = null;
    try {

      objReaderFile1 = readTxtFile(file1);
      System.out.print(objReaderFile1.readLine());
      objReaderFile2 = readTxtFile(file2);
    }
    catch (NullPointerException f) {
      results.add("Provide 2 text files with rest api url's with new line delimiter");
      return results;
    }

    while (true) {
      String firstUrl = objReaderFile1.readLine();
      String secondUrl = objReaderFile2.readLine();
      try {
        if (firstUrl == null || secondUrl == null)
          break;
        String response = apiUtilInterface.getRestAPI(firstUrl);
        if (response.equals("{}"))
          results.add("Resource not found for this url");
        boolean check = apiUtilInterface.isCompatibleWithUserData(response);

        boolean checkwithListData = apiUtilInterface.isCompatableWithPaginationPojo(response);
        String response2 = apiUtilInterface.getRestAPI(secondUrl);
        if (check == true && checkwithListData == false) {
          UserDataPojo data = new Gson().fromJson(response, UserDataPojo.class);
          UserDataPojo data2 = new Gson().fromJson(response, UserDataPojo.class);
          if (data.getUserData().getId() == data2.getUserData().getId()
              && data.getUserData().getFirst_name().equals(data2.getUserData().getFirst_name())
              && data.getUserData().getLast_name().equals(data2.getUserData().getLast_name())
              && data.getUserData().getAvatar().equals(data2.getUserData().getAvatar())) {
            results.add(firstUrl + " equals " + secondUrl);
          } else {
            results.add(firstUrl + " not equals " + secondUrl);
          }
        } else if (check == false && checkwithListData == true) {
          PaginationPojo data = new Gson().fromJson(response, PaginationPojo.class);
          PaginationPojo data2 = new Gson().fromJson(response2, PaginationPojo.class);
          if (data.getTotal_pages() == data2.getTotal_pages() && data.getPage() == data2.getPage()
              && data.getPer_page() == data2.getPer_page() && data.getTotal() == data2.getTotal()) {
            results.add(firstUrl + " equals " + secondUrl);
          }
        }
      }
      catch (NullPointerException g) {
        results.add("Provide 2 text files with rest api url's with new line delimiter");
      }
      catch (IllegalStateException e) {
        results.add(firstUrl + " not equals " + secondUrl);
      } catch (JsonSyntaxException j) {
        results.add(firstUrl + " not equals " + secondUrl);
      } catch (Exception u) {
        results.add("Please enter valid url");
      }
    }
    objReaderFile1.close();
    objReaderFile2.close();
    for (String output:results
         ) {
      logger.info(output);

    }
    return results;
  }
}
