package com.example.androidtrainingtosasanka.intentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidtrainingtosasanka.R;

public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        startIntentService();
    }

    void startIntentService() {
        Intent intent = new Intent(IntentServiceActivity.this, TimeCountService.class);
        intent.putExtra("foo", "bar");
        startService(intent);
    }
}
