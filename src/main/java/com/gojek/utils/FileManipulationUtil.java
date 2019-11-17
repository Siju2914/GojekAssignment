package com.gojek.utils;

import com.gojek.exceptions.UnsupportedFileTypeException;
import com.gojek.pojos.PaginationPojo;
import com.gojek.pojos.UserDataPojo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    else return "";

  }

  public List<String> readTxtFile(String fileName) throws IOException, UnsupportedFileTypeException {
    File inputFile = new File(classLoader.getResource(fileName).getFile());
    List<String> endURL = new ArrayList<String>();
    String fileExtension = getFileExtension(inputFile);
    BufferedReader inputStream = null;
    try {
      if (fileExtension.equals("txt")) {
        inputStream = new BufferedReader(new FileReader(inputFile));

        String line;
        while ((line = inputStream.readLine()) != null) {
          endURL.add(line);
        }
      }

    } catch (Exception e) {
      throw new UnsupportedFileTypeException();
    }

    return endURL;

  }

  public List<String> IComparator(String file1, String file2) throws IOException {
    List<String> results = new ArrayList<String>();
    APIUtilInterface apiUtilInterfaceObject = new APIUtil();
    BufferedReader objReaderFile1 = null;
    BufferedReader objReaderFile2 = null;
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      objReaderFile1 = new BufferedReader(new FileReader(classLoader.getResource(file1).getFile()));
      objReaderFile2 = new BufferedReader(new FileReader(classLoader.getResource(file2).getFile()));
    } catch (NullPointerException f) {
      results.add("Provide 2 text files with rest api url's with new line delimiter");
      return results;
    }

    while (true) {
      String firstUrl = objReaderFile1.readLine();
      String secondUrl = objReaderFile2.readLine();
      try {
        if (firstUrl == null || secondUrl == null)
          break;
        String responseFromFirstUrl = apiUtilInterfaceObject.getRestAPI(firstUrl);
        if (responseFromFirstUrl.equals("{}"))
          results.add("Resource not found for this url");
        boolean isJSONResponseCompatible = apiUtilInterfaceObject.isCompatibleWithUserData(responseFromFirstUrl);
        boolean isListDataCompatible = apiUtilInterfaceObject.isCompatableWithPaginationPojo(responseFromFirstUrl);
        String responseFromSecondUrl = apiUtilInterfaceObject.getRestAPI(secondUrl);
        if (isJSONResponseCompatible == true && isListDataCompatible == false) {
          UserDataPojo userDataFirst = new Gson().fromJson(responseFromFirstUrl, UserDataPojo.class);
          UserDataPojo userDataSecond = new Gson().fromJson(responseFromSecondUrl, UserDataPojo.class);
          if (userDataFirst.getUserData().getId() == userDataSecond.getUserData().getId()
              && userDataFirst.getUserData().getFirst_name().equals(userDataSecond.getUserData().getFirst_name())
              && userDataFirst.getUserData().getLast_name().equals(userDataSecond.getUserData().getLast_name())
              && userDataFirst.getUserData().getAvatar().equals(userDataSecond.getUserData().getAvatar())) {
            results.add(firstUrl + " equals " + secondUrl);
          } else {
            results.add(firstUrl + " not equals " + secondUrl);
          }
        } else if (isJSONResponseCompatible == false && isListDataCompatible == true) {
          PaginationPojo paginationDataFromFirst = new Gson().fromJson(responseFromFirstUrl, PaginationPojo.class);
          PaginationPojo paginationDataFromSecond = new Gson().fromJson(responseFromSecondUrl, PaginationPojo.class);
          if (paginationDataFromFirst.getTotal_pages() == paginationDataFromSecond.getTotal_pages() && paginationDataFromFirst.getPage() == paginationDataFromSecond.getPage()
              && paginationDataFromFirst.getPer_page() == paginationDataFromSecond.getPer_page() && paginationDataFromFirst.getTotal() == paginationDataFromSecond.getTotal()) {
            results.add(firstUrl + " equals " + secondUrl);
          }
        }
      } catch (NullPointerException g) {
        results.add("Provide 2 text files with rest api url's with new line delimiter");
      } catch (IllegalStateException e) {
        results.add(firstUrl + " not equals " + secondUrl);
      } catch (JsonSyntaxException j) {
        results.add(firstUrl + " not equals " + secondUrl);
      } catch (Exception u) {
        results.add("Please enter valid url");
      }
    }
    objReaderFile1.close();
    objReaderFile2.close();
    return results;
  }
}
