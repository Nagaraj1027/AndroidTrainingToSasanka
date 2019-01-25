package com.example.androidtrainingtosasanka.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidtrainingtosasanka.MainActivity;
import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.activitylifecycle.FirstActivity;
import com.example.androidtrainingtosasanka.activitylifecycle.SecondActivity;
import com.example.androidtrainingtosasanka.appclass.AppConstants;

public class ExplicitIntentActivity extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        btn1 = (Button) findViewById(R.id.btn1);

        String s = getIntent().getStringExtra(AppConstants.KEY_USERNAME);
        Log.e("Received value is", s + "");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_first = new Intent(ExplicitIntentActivity.this, MainActivity.class);
                startActivity(to_first);
            }
        });
    }
}
