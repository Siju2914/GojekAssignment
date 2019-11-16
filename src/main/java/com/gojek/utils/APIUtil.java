package com.gojek.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class APIUtil implements APIUtilInterface{
  public String getRestAPI(String endURLPath) throws Exception {
    String response = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(endURLPath).asString();
    return response;
  }
}
