package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidtrainingtosasanka.activitylifecycle.FirstActivity;
import com.example.androidtrainingtosasanka.appclass.AppConstants;
import com.example.androidtrainingtosasanka.dynamicfragments.DynamicFragmntsActivity;
import com.example.androidtrainingtosasanka.intent.ExplicitIntentActivity;
import com.example.androidtrainingtosasanka.staticfragments.StaticFragmentsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    Button btn1, btn2, btn3, btn4, btn5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        Log.e("onCreate MainActivity", "onCreate MainActivity");

        //Hello Git
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    void gotoActivityLifeCycle() {
        Intent to_first = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(to_first);
    }

    void gotoExplicitIntent() {
        Intent to_explicit = new Intent(MainActivity.this, ExplicitIntentActivity.class);
        to_explicit.putExtra(AppConstants.KEY_USERNAME, "Android");
        startActivity(to_explicit);
    }

    void gotoImplicitIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    void gotoStaticFragments() {
        Intent to_staticfragement = new Intent(MainActivity.this, StaticFragmentsActivity.class);
        startActivity(to_staticfragement);
    }

    void gotoDynamicFragments() {
        Intent to_staticfragement = new Intent(MainActivity.this, DynamicFragmntsActivity.class);
        startActivity(to_staticfragement);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn1:
                gotoActivityLifeCycle();
                break;

            case R.id.btn2:
                gotoExplicitIntent();
                break;

            case R.id.btn3:
                gotoImplicitIntent();
                break;

            case R.id.btn4:
                gotoStaticFragments();
                break;
            case R.id.btn5:
                gotoDynamicFragments();
                break;
        }
    }
}
