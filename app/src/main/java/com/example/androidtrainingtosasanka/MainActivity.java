package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidtrainingtosasanka.activitylifecycle.FirstActivity;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        //Hello Git
        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to_first = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(to_first);

            }
        });
    }
}
