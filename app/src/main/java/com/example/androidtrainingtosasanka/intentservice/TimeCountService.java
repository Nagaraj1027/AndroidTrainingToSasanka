package com.example.androidtrainingtosasanka.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class TimeCountService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public TimeCountService(String name) {
        super(name);
    }

    public TimeCountService() {
        super("Hello");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Looper.prepare();
        String name = intent.getStringExtra("foo");
        System.out.println("name is: " + name);
        //Example1
        for (int i = 0; i < 10; i++) {
            System.out.println("i is: " + i + "");
            //Intent service runs onn worker thread and Toast message runs on main thread so toast will not work here
            //Toast.makeText(getApplicationContext(), i + "", Toast.LENGTH_LONG).show();
        }
        updateUIusingBroadcastReceiver("Android", "Gloabal");
        //Example2
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println("Time remaining: " + millisUntilFinished / 1000);
                //Intent service runs onn worker thread and Toast message runs on main thread so toast will not work here
                // Toast.makeText(getApplicationContext(), millisUntilFinished / 1000 + "", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish() {
                System.out.println("done");
                //Intent service runs onn worker thread and Toast message runs on main thread so toast will not work here
                //Toast.makeText(getApplicationContext(), "Done intent service", Toast.LENGTH_LONG).show();
                updateUIusingBroadcastReceiver("Android", "Gloabal");
                Looper.myLooper().quit();
            }
        }.start();
        Looper.loop();

        //Example3
        //Make a API call using volley/Retrofit, Pass your received JSONObject to BroadcastReceiver for updating UI

    }

    void updateUIusingBroadcastReceiver(String str, String str1) {
        Intent intentBroadcast = new Intent(this, MyIntentReceiver.class);
        intentBroadcast.putExtra("name", str);
        intentBroadcast.putExtra("locality", str1);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intentBroadcast);
    }
}
