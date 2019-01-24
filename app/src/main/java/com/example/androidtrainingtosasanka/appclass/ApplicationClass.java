package com.example.androidtrainingtosasanka.appclass;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;


public class ApplicationClass extends Application {

    private static ApplicationClass currentApplication = null;

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate AppClass", "onCreate ApplicationClass");

        sharedPreferences = getSharedPreferences("Userdetails", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static ApplicationClass getInstance() {
        return currentApplication;
    }

    public static void putUserNameIntoSp(String name) {
        editor.putString("keyname", name);
        editor.commit();
    }

   public static String getUsernameFromSp() {
        String username = sharedPreferences.getString("keyname", "");

        return username;
    }

    public static void putPasswordIntoSp(String password) {
        editor.putString("keypassword", password);
        editor.commit();
    }

  public   static String getPasswordFromSp() {
        String password = sharedPreferences.getString("keypassword", "");

        return password;
    }

}

