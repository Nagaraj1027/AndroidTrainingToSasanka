package com.example.androidtrainingtosasanka.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.androidtrainingtosasanka.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        startIntentService();
    }

    void startIntentService() {
        Intent intent = new Intent(ServiceActivity.this, ServiceTimeCount.class);
        intent.putExtra("foo", "bar");
        startService(intent);
    }

    public void stopService(){
        Intent intent=new Intent(this,ServiceTimeCount.class);
        stopService(intent);
    }
}
