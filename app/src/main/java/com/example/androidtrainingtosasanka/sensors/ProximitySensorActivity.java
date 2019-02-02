package com.example.androidtrainingtosasanka.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.androidtrainingtosasanka.R;

public class ProximitySensorActivity extends AppCompatActivity implements SensorEventListener {

  /*  Proximity sensors would be useful to reveal the nearness of an object to the phone.
    We might often experience that our phone screen would turn off when we bring the phone to our ears
    when we are in a call and the screen will turn on when we take it back.
    This is because the proximity sensor recognizes the object near the phone.*/

    private SensorManager mSensorManager;
    private Sensor mSensor;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        initViews();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Proximity Sensor");
        }

        iv = (ImageView) findViewById(R.id.imageView1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) {
            iv.setImageResource(R.drawable.done);
        } else {
            iv.setImageResource(R.drawable.avatarupload);
        }
    }
}