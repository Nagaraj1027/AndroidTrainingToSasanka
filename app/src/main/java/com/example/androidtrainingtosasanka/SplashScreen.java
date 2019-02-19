package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidtrainingtosasanka.appclass.ApplicationClass;
import com.example.androidtrainingtosasanka.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startThread();
       //usingHandler();
    }

    void startThread() {
        /****** Create Thread that will sleep for 5 seconds****/
        Thread thread = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(3 * 1000);

                    // After 5 seconds redirect to another intent

                    if (ApplicationClass.getRememberMe()) {
                        gotoMainActivity();
                    } else {
                        gotoLoginActivity();
                    }
                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        thread.start();
    }

    void usingHandler() {
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override
            public void run() {

                if (ApplicationClass.getRememberMe()) {
                    gotoMainActivity();
                } else {
                    gotoLoginActivity();
                }
                // close this activity
                finish();
            }
        }, 3 * 1000); // wait for 3 seconds
    }

    void gotoMainActivity() {
        Intent to_home = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(to_home);
    }

    void gotoLoginActivity() {
        Intent i = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(i);
    }
}
