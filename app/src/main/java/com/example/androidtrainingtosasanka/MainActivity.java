package com.example.androidtrainingtosasanka;

import android.content.Intent;
import android.net.Uri;
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
import com.example.androidtrainingtosasanka.intent.ExplicitIntentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView lv;
    Button btn1, btn2, btn3, btnCall, btnClear;
    EditText text1;
    String number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        Log.e("onCreate MainActivity", "onCreate MainActivity");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnClear = (Button) findViewById(R.id.btnClear);

        text1 = (EditText) findViewById(R.id.editText1);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnClear.setOnClickListener(this);

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

    void gotoCall() {
        number = text1.getText().toString();
        final Intent intent_call = new Intent(Intent.ACTION_CALL);
        intent_call.setData(Uri.parse("tel:" + number));
    }

    void clearPreferences() {
        ApplicationClass.setRememberMe(false);
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

            case R.id.btnCall:
                gotoCall();
                break;

            case R.id.btnClear:
                clearPreferences();
                break;

        }


    }
}
