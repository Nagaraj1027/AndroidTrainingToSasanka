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
import com.example.androidtrainingtosasanka.database.DataActivity;
import com.example.androidtrainingtosasanka.dynamicfragments.DynamicFragmntsActivity;
import com.example.androidtrainingtosasanka.galleryimages.Gallery;
import com.example.androidtrainingtosasanka.imageprocessing.SimpleImageActivity2;
import com.example.androidtrainingtosasanka.intent.ExplicitIntentActivity;
import com.example.androidtrainingtosasanka.intentservice.IntentServiceActivity;
import com.example.androidtrainingtosasanka.jsonparser.JSonActivity;
import com.example.androidtrainingtosasanka.listview.ListviewActivity;
import com.example.androidtrainingtosasanka.notificationsFcm.NotificationActivity;
import com.example.androidtrainingtosasanka.recyclerview.RecyclerViewExample;
import com.example.androidtrainingtosasanka.sensors.AccelerometerSensorActivity;
import com.example.androidtrainingtosasanka.sensors.ProximitySensorActivity;
import com.example.androidtrainingtosasanka.service.ServiceActivity;
import com.example.androidtrainingtosasanka.staticfragments.StaticFragmentsActivity;
import com.example.androidtrainingtosasanka.volleyserevercalls.VollleyExample1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    EditText text1;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        Log.e("onCreate Gallery", "onCreate Gallery");

        findViews();
    }

    private void findViews() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn13 = (Button) findViewById(R.id.btn13);
        btn14 = (Button) findViewById(R.id.btn14);
        btn15 = (Button) findViewById(R.id.btn15);
        btn16 = (Button) findViewById(R.id.btn16);
        btn17 = (Button) findViewById(R.id.btn17);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
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

    void gotoRecyclerViewExample() {
        Intent to_recyclerViewExample = new Intent(MainActivity.this, RecyclerViewExample.class);
        startActivity(to_recyclerViewExample);
    }

    void gotoDbExample() {
        Intent to_DbActivity = new Intent(MainActivity.this, DataActivity.class);
        startActivity(to_DbActivity);
    }

    void gotoImageProcessing() {
        Intent to_DbActivity = new Intent(MainActivity.this, SimpleImageActivity2.class);
        startActivity(to_DbActivity);
    }

    void gotoAccelerometerSensor() {
        Intent to_DbActivity = new Intent(MainActivity.this, AccelerometerSensorActivity.class);
        startActivity(to_DbActivity);
    }

    void gotoProximitySensor() {
        Intent to_DbActivity = new Intent(MainActivity.this, ProximitySensorActivity.class);
        startActivity(to_DbActivity);
    }

    void gotoNotification() {
        Intent to_explicitAct = new Intent(MainActivity.this, NotificationActivity.class);
        startActivity(to_explicitAct);
        //finish();
    }

    void gotoReadJson() {
        Intent to_explicitAct = new Intent(MainActivity.this, JSonActivity.class);
        startActivity(to_explicitAct);
        //finish();
    }

    void gotoVolleyExample() {
        Intent to_explicitAct = new Intent(MainActivity.this, VollleyExample1.class);
        startActivity(to_explicitAct);
        //finish();
    }

    void gotoShowGalleryImages() {
        Intent to_explicitAct = new Intent(MainActivity.this, Gallery.class);
        startActivity(to_explicitAct);
        //finish();
    }

    void gotoIntentService() {
        Intent to_explicitAct = new Intent(MainActivity.this, IntentServiceActivity.class);
        startActivity(to_explicitAct);
    }

    void gotoService() {
        Intent to_explicitAct = new Intent(MainActivity.this, ServiceActivity.class);
        startActivity(to_explicitAct);
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
            case R.id.btn7:
                gotoRecyclerViewExample();
                break;

            case R.id.btn8:
                gotoDbExample();
                break;
            case R.id.btn9:
                gotoImageProcessing();
                break;
            case R.id.btn10:
                gotoAccelerometerSensor();
                break;
            case R.id.btn11:
                gotoProximitySensor();
                break;
            case R.id.btn12:
                gotoNotification();
                break;
            case R.id.btn13:
                gotoReadJson();
                break;

            case R.id.btn14:
                gotoVolleyExample();
                break;
            case R.id.btn15:
                gotoShowGalleryImages();
                break;
            case R.id.btn16:
                gotoIntentService();
                break;

            case R.id.btn17:
                gotoService();
                break;

        }
    }
}
