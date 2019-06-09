package com.example.gyroscopic;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Sensor sensor;
    Sensor sensor1;
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    //private TriggerEventListener triggerEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);



        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                //float value = event.values[1] * SensorManager.GRAVITY_EARTH;
               if(event.values[1] > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }else if (event.values[1] < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
               Log.d("value", String.valueOf(event.values[1]));

                /*if(event.values[0] > 0.1){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if(event.values[0] < 0.1){
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                
                Log.d("value", String.valueOf(event.values[0]));*/


            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

       /* triggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                // Do work]
                Log.d("value", String.valueOf(event.values[1]));
            }
        }; */




    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.requestTriggerSensor(triggerEventListener, sensor);

    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        //sensorManager.cancelTriggerSensor(triggerEventListener, sensor);

    }
}
