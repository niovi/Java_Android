package com.example.katerina.collisionalert.Sensors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class SensorService extends Service {

    private static final String TAG = "SensorService";

    public SensorService() {
        super();
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");

    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");
        //New thread for each sensor
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Light_Sensor(getApplicationContext());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Proximity_Sensor(getApplicationContext());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Accelerator_Sensor(getApplicationContext());
            }
        }).start();
        //always running
        return Service.START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
        super.onDestroy();
    }

}
