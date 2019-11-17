package com.gojek.pojos;

import java.util.ArrayList;
import java.util.List;

public class UserDataList {

  List<UserData> userDataList = new ArrayList<UserData>();

  public List<UserData> getUserDataList() {
    return userDataList;
  }

  public void setUserDataList(List<UserData> userDataList) {
    this.userDataList = userDataList;
  }
}
