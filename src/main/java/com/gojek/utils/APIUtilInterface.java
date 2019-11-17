package com.gojek.utils;

/**
 * This interface has all the methods for api manipulation.
 * @author siju
 */
public interface APIUtilInterface {
  //This method will hit the https endURL and returns the response.
  public String getRestAPI(String endURLPath) throws Exception;
  //This method will check whether the data is compatible with the UserDataPojo.
  public boolean isCompatibleWithUserData(String jsonResponse);
  //This method will check whether the paginated data is compatible with the PaginationPojo.
  public boolean isCompatableWithPaginationPojo(String request);
}
