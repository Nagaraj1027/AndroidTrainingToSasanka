package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidtrainingtosasanka.activitylifecycle.FirstActivity;
import com.example.androidtrainingtosasanka.intent.ExplicitIntentActivity;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn1, btn2, btn3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        //Hello Git
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivityLifeCycle();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoExplicitIntent();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoImplicitIntent();
            }
        });
    }

    void gotoImplicitIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    void gotoExplicitIntent() {
        Intent to_explicit = new Intent(MainActivity.this, ExplicitIntentActivity.class);
        to_explicit.putExtra("key", "Android");
        startActivity(to_explicit);

    }

    void gotoActivityLifeCycle() {
        Intent to_first = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(to_first);
    }
}
