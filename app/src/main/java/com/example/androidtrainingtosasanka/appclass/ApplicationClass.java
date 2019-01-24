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

    static String getUsername() {
        String username = sharedPreferences.getString("keyname", "");

        return username;
    }
}
