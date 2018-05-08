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

import com.example.katerina.collisionalert.Settings.Collision_Settings;
import com.example.katerina.collisionalert.Notify;


import static android.content.Context.SENSOR_SERVICE;
import static java.lang.StrictMath.abs;


class Accelerator_Sensor {

    private static final String TAG = "Accelerator";
    private int time_interval;

    private Double Accelerator_reading_X =0.0;
    private Double Accelerator_reading_X_old =0.0;
    private Double Accelerator_threshold_X =0.0;

    private Double Accelerator_reading_Y =0.0;
    private Double Accelerator_reading_Y_old =0.0;
    private Double Accelerator_threshold_Y =0.0;

    private Double Accelerator_reading_Z =0.0;
    private Double Accelerator_reading_Z_old =0.0;
    private Double Accelerator_threshold_Z =0.0;

    //private double[] gravity = new double[3];
    private Handler handler;
    private boolean flag = false;


    private void check_accelerator(Double reading, Double threshold, Context context){

        if(abs(reading)> threshold){

            Log.i(TAG, "Accelerator TRUE");


            new Notify(context,time_interval);

        }

        else  Log.i(TAG, "Accelerator FALSE");}



    Accelerator_Sensor(final Context context){

        final Collision_Settings prefs5=Collision_Settings.getInstance(context);
        Accelerator_threshold_X = Double.parseDouble(prefs5.getData("threshold_04_X"));
        Accelerator_threshold_Y = Double.parseDouble(prefs5.getData("threshold_04_Y"));
        Accelerator_threshold_Z = Double.parseDouble(prefs5.getData("threshold_04_Z"));
        time_interval = (int)Double.parseDouble(prefs5.getData("time_interval_04"));
        String Accelerator_available;
        handler = new Handler(Looper.getMainLooper());




        final SensorEventListener AcceleratorSensorListener = new SensorEventListener() {

            //@Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }

            //@Override
            public void onSensorChanged(SensorEvent event) {
                // alpha is calculated as t / (t + dT)
                // with t, the low-pass filter's time-constant
                // and dT, the event delivery rate
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//                    final float alpha= (float) 0.8;
                    if (flag) {
//                        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
//                        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
//                        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                        String reading_received_X=String.valueOf(event.values[0]);
                        String reading_received_Y=String.valueOf(event.values[1]);
                        String reading_received_Z=String.valueOf(event.values[2]);

                        Accelerator_reading_X =Double.parseDouble(reading_received_X);
                        Accelerator_reading_Y =Double.parseDouble(reading_received_Y);
                        Accelerator_reading_Z =Double.parseDouble(reading_received_Z);


                        if(!Accelerator_reading_X.equals(Accelerator_reading_X_old)){
                            prefs5.saveData("value_04_X",reading_received_X);
                            Accelerator_reading_X_old = Accelerator_reading_X;
                        }
                        if(!Accelerator_reading_Y.equals(Accelerator_reading_Y_old)){
                            prefs5.saveData("value_04_Y",reading_received_Y);
                            Accelerator_reading_Y_old = Accelerator_reading_Y;
                        }
                        if(!Accelerator_reading_Z.equals(Accelerator_reading_Z_old)){
                            prefs5.saveData("value_04_Z",reading_received_Z);
                            Accelerator_reading_Z_old = Accelerator_reading_Z;
                        }

                        Log.i(TAG,"New accelerator value returned from sensor");

                        check_accelerator(Accelerator_reading_X, Accelerator_threshold_X,context);
                        check_accelerator(Accelerator_reading_Y, Accelerator_threshold_Y,context);
                        check_accelerator(Accelerator_reading_Z, Accelerator_threshold_Z,context);


                        flag = false;}
                }}};


        final SensorManager mySensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        final Sensor AcceleratorSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (AcceleratorSensor != null) {
            Log.i(TAG,"Accelerator Sensor available");
            Accelerator_available="Sensor.TYPE_ACCELERATION Available";
            prefs5.saveData("availability_04",Accelerator_available);
            mySensorManager.registerListener(AcceleratorSensorListener, AcceleratorSensor,SensorManager.SENSOR_DELAY_NORMAL);
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
            Log.i(TAG," Accelerator Sensor not available");
            Accelerator_available="Sensor.TYPE_ACCELERATION NOT Available";
            prefs5.saveData("availability_04",Accelerator_available);

        }



        // handler for received data from service
        final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Collision_Settings.NEW_TIME_INTERVAL_04)) {
                    time_interval = (int)Double.parseDouble(intent.getStringExtra("time_interval_04"));

                }
                if (intent.getAction().equals(Collision_Settings.NEW_THRESHOLD_04_X)) {
                    Accelerator_threshold_X = Double.parseDouble(intent.getStringExtra("threshold_04_X"));
                    check_accelerator(Accelerator_reading_X, Accelerator_threshold_X,context);
                }
                if (intent.getAction().equals(Collision_Settings.NEW_THRESHOLD_04_Y)) {
                    Accelerator_threshold_Y = Double.parseDouble(intent.getStringExtra("threshold_04_Y"));
                    check_accelerator(Accelerator_reading_Y, Accelerator_threshold_Y,context);
                }
                if (intent.getAction().equals(Collision_Settings.NEW_THRESHOLD_04_Z)) {
                    Accelerator_threshold_Z = Double.parseDouble(intent.getStringExtra("threshold_04_Z"));
                    check_accelerator(Accelerator_reading_Z, Accelerator_threshold_Z,context);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Collision_Settings.NEW_TIME_INTERVAL_04);
        LocalBroadcastManager bm = LocalBroadcastManager.getInstance(context);
        bm.registerReceiver(mBroadcastReceiver, filter);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(Collision_Settings.NEW_THRESHOLD_04_X);
        LocalBroadcastManager bm3 = LocalBroadcastManager.getInstance(context);
        bm3.registerReceiver(mBroadcastReceiver, filter3);

        IntentFilter filter4 = new IntentFilter();
        filter4.addAction(Collision_Settings.NEW_THRESHOLD_04_Y);
        LocalBroadcastManager bm4 = LocalBroadcastManager.getInstance(context);
        bm4.registerReceiver(mBroadcastReceiver, filter4);

        IntentFilter filter5 = new IntentFilter();
        filter5.addAction(Collision_Settings.NEW_THRESHOLD_04_Z);
        LocalBroadcastManager bm5 = LocalBroadcastManager.getInstance(context);
        bm5.registerReceiver(mBroadcastReceiver, filter5);


    }




}
