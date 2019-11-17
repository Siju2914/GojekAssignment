package com.gojek.utils;

public interface APIUtilInterface {
  public String getRestAPI(String endURLPath) throws Exception;
  public boolean isCompatibleWithUserData(String jsonResponse);
  public boolean isCompatableWithPaginationPojo(String request);
}
