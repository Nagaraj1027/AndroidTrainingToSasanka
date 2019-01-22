package com.example.androidtrainingtosasanka.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidtrainingtosasanka.MainActivity;
import com.example.androidtrainingtosasanka.R;

public class FirstActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.e("onCreate First act","onCreate First act");

        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to_first = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(to_first);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart First act","onStart First act");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume First act","onResume First act");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause First act","onPause First act");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop First act","onStop First act");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy First act","onDestroy First act");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart First act","onRestart First act");
    }
}
