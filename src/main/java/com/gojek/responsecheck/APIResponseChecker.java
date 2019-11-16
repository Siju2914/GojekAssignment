package com.gojek.responsecheck;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class APIResponseChecker {

  public String getRestAPI(String endURLPath){
    return RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).get(endURLPath).asString();

  }
}
