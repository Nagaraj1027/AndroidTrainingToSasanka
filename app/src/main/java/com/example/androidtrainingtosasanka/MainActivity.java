package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.androidtrainingtosasanka.activitylifecycle.FirstActivity;
import com.example.androidtrainingtosasanka.appclass.AppConstants;
import com.example.androidtrainingtosasanka.appclass.ApplicationClass;
import com.example.androidtrainingtosasanka.dynamicfragments.DynamicFragmntsActivity;
import com.example.androidtrainingtosasanka.intent.ExplicitIntentActivity;
import com.example.androidtrainingtosasanka.listview.ListviewActivity;
import com.example.androidtrainingtosasanka.staticfragments.StaticFragmentsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    EditText text1;
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        Log.e("onCreate MainActivity", "onCreate MainActivity");

        findViews();
    }

    private void findViews() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
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
        Log.e("jnfjbfvv", "jbcbhd");
        Intent intent_dial = new Intent(Intent.ACTION_DIAL);
        intent_dial.setData(Uri.parse("tel:" + "1234567890"));
        startActivity(intent_dial);
    }

    void gotoStaticFragments() {
        Intent to_staticfragement = new Intent(MainActivity.this, StaticFragmentsActivity.class);
        startActivity(to_staticfragement);
    }

    void gotoDynamicFragments() {
        Intent to_dynamicFragments = new Intent(MainActivity.this, DynamicFragmntsActivity.class);
        startActivity(to_dynamicFragments);
    }

    void gotolistViewExample() {
        Intent to_listViewExample = new Intent(MainActivity.this, ListviewActivity.class);
        startActivity(to_listViewExample);
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

            case R.id.btn6:
                gotolistViewExample();
                break;
        }
    }
}
