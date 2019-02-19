package com.example.androidtrainingtosasanka.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyIntentReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        System.out.println("inside onReceive ");
        String name = intent.getStringExtra("name");
        String locality = intent.getStringExtra("locality");

        Toast.makeText(context, "name is" + name, Toast.LENGTH_LONG).show();
        Toast.makeText(context, "locality is" + locality, Toast.LENGTH_LONG).show();
    }
}
