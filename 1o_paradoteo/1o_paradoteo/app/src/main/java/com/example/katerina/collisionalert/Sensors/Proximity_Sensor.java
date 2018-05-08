package com.example.katerina.collisionalert.Sensors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.katerina.collisionalert.Notify;
import com.example.katerina.collisionalert.Settings.Collision_Settings;



import static android.content.Context.SENSOR_SERVICE;



class Proximity_Sensor {

    private static final String TAG = "Proximity Sensor";
    private Double proximity_reading=0.0;
    private Double proximity_reading_old=0.0;
    private Double proximity_threshold=0.0;
    private int time_interval;
    private Handler handler;
    private boolean flag = false;


    private void check_proximity(Context context) {

        if (proximity_reading < proximity_threshold) {
            Log.i(TAG, "PROXIMITY TRUE");

            new Notify(context, time_interval);
        } else {
            Log.i(TAG, "PROXIMITY FALSE");
        }
    }


    Proximity_Sensor(final Context context){

        final Collision_Settings prefs4=Collision_Settings.getInstance(context);
        proximity_threshold = Double.parseDouble(prefs4.getData("threshold_02"));
        time_interval = (int)Double.parseDouble(prefs4.getData("time_interval_02"));
        handler = new Handler(Looper.getMainLooper());





        final SensorEventListener ProximitySensorListener = new SensorEventListener() {

            //@Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }

            //@Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (flag) {
                    String proximity_reading_received=String.valueOf(event.values[0]);
                    proximity_reading=Double.parseDouble(proximity_reading_received);
                    if(!proximity_reading.equals(proximity_reading_old)){
                        prefs4.saveData("value_02",proximity_reading_received);
                        proximity_reading_old=proximity_reading;
                    }
                    Log.i(TAG,"New proximity value returned from sensor");

                    check_proximity(context);

                        flag = false;}
                }}};


        final SensorManager mySensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        final Sensor ProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        String proximity_available;
        if (ProximitySensor != null) {
            Log.i(TAG,"Proximity Sensor available");
            proximity_available ="Sensor.TYPE_PROXIMITY Available";
            prefs4.saveData("availability_02", proximity_available);
            mySensorManager.registerListener(ProximitySensorListener, ProximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            Runnable processSensors = new Runnable() {
                @Override
                public void run() {
                    // Do work with the sensor values.

                    flag = true;
                    // The Runnable is posted to run again here:
                    handler.postDelayed(this, time_interval);
                }
            };
            handler.post(processSensors);


        } else {
            Log.i(TAG,"Proximity Sensor not available");
            proximity_available ="Sensor.TYPE_PROXIMITY NOT Available";
            prefs4.saveData("availability_02", proximity_available);

        }



        // handler for received data from service
        final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Collision_Settings.NEW_TIME_INTERVAL_02)) {
                    time_interval = (int)Double.parseDouble(intent.getStringExtra("time_interval_02"));
                }
                if (intent.getAction().equals(Collision_Settings.NEW_THRESHOLD_02)) {
                    proximity_threshold = Double.parseDouble(intent.getStringExtra("threshold_02"));
                    check_proximity(context);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Collision_Settings.NEW_TIME_INTERVAL_02);
        LocalBroadcastManager bm = LocalBroadcastManager.getInstance(context);
        bm.registerReceiver(mBroadcastReceiver, filter);

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(Collision_Settings.NEW_THRESHOLD_02);
        LocalBroadcastManager bm2 = LocalBroadcastManager.getInstance(context);
        bm2.registerReceiver(mBroadcastReceiver, filter2);


    }

    
}
