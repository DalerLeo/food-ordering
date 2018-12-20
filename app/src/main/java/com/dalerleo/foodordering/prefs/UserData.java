package com.dalerleo.foodordering.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dalerleo.foodordering.app.App;

import static com.dalerleo.foodordering.LoginActivity.USER_REF;

public class UserData {
  private String PREF_NAME = "prefs";
  private String USER_NAME = "username";
  private Context context;

  public UserData(){
    this.context = App.getsInstance().getApplicationContext();
  }

  public void saveUsername(String username) {
    getSharedPrefs().edit().putString(USER_NAME,username).apply();
  }

  public String getUsername() {
    return getSharedPrefs().getString(USER_NAME, "");
  }

  public String getUserPath() {
    String strArray[] = getUsername().split("@");
    return strArray[0];
  }



  public SharedPreferences getSharedPrefs() {
    return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
  }

}
