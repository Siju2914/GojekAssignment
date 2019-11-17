package com.gojek.utils;

import com.gojek.pojos.PaginationPojo;
import com.gojek.pojos.UserDataPojo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Util to handle all the methods from APIUtilInterface
 * @author siju
 */
public class APIUtil implements APIUtilInterface {
  /**
   * {@inheritDoc}
   * @param endURLPath
   * @return
   * @throws Exception
   */
  public String getRestAPI(String endURLPath) throws Exception {
    String response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(endURLPath).asString();
    return response;
  }

  /**
   * {@inheritDoc}
   * @param jsonResponse
   * @return
   */
  public boolean isCompatibleWithUserData(String jsonResponse) {
    try {
      UserDataPojo dataCheck = new Gson().fromJson(jsonResponse, UserDataPojo.class);
      return true;
    } catch (JsonSyntaxException i) {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   * @param request
   * @return
   */
  public boolean isCompatableWithPaginationPojo(String request) {
    try {
      PaginationPojo paginationPojo = new Gson().fromJson(request, PaginationPojo.class);
      return true;
    } catch (JsonSyntaxException i) {
      return false;
    }
  }
}
