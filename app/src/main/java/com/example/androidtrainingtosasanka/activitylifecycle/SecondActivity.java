package com.example.androidtrainingtosasanka.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidtrainingtosasanka.MainActivity;
import com.example.androidtrainingtosasanka.R;

public class SecondActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.e("onCreate Second act","onCreate Second act");

        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_first = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(to_first);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart Second act","onStart Second act");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume Second act","onResume Second act");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause Second act","onPause Second act");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop Second act","onStop Second act");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy Second act","onDestroy Second act");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart Second act","onRestart Second act");
    }
}
