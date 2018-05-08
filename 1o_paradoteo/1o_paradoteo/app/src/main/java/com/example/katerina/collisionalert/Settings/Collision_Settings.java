package com.example.katerina.collisionalert.Settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.katerina.collisionalert.R;


public class Collision_Settings {

    private Context set_context;

    // light sensor
    public static final String NEW_VALUE_01 = "value_01";
    public static final String NEW_AVAILABILITY_01 = "availability_01";
    public static final String NEW_THRESHOLD_01 = "threshold_01";
    public static final String NEW_TIME_INTERVAL_01 = "time_interval_01";


    // proximity sensor
    public static final String NEW_VALUE_02 = "value_02";
    public static final String NEW_AVAILABILITY_02 = "availability_02";
    public static final String NEW_THRESHOLD_02 = "threshold_02";
    public static final String NEW_TIME_INTERVAL_02 = "time_interval_02";

    // accelerator sensor
    public static final String NEW_AVAILABILITY_04 = "availability_04";
    public static final String NEW_TIME_INTERVAL_04= "time_interval_04";
    public static final String NEW_VALUE_04_X = "value_04_X";
    public static final String NEW_THRESHOLD_04_X = "threshold_04_X";
    public static final String NEW_VALUE_04_Y = "value_04_Y";
    public static final String NEW_THRESHOLD_04_Y = "threshold_04_Y";
    public static final String NEW_VALUE_04_Z = "value_04_Z";
    public static final String NEW_THRESHOLD_04_Z = "threshold_04_Z";





    @SuppressLint("StaticFieldLeak")
    private static Collision_Settings Set_preferences;
    private SharedPreferences Stored_settings;
    private static final String TAG = "Shared Preferences";


    public static Collision_Settings getInstance(Context context) {
        if (Set_preferences == null) {
            Set_preferences = new Collision_Settings(context);
            Log.i(TAG,"New collision settings created");
        }
        return Set_preferences;
    }

    private Collision_Settings(Context context) {
        Stored_settings = context.getSharedPreferences(context.getString(R.string.App_Settings), Context.MODE_PRIVATE);
        set_context=context;
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = Stored_settings.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
        Log.i(TAG,"New commit to settings "+key);
        // broadcast change every time a new commit occurs
        final LocalBroadcastManager bm = LocalBroadcastManager.getInstance(set_context);
        Intent intent = new Intent(key);
        intent.putExtra(key, getData(key));
        bm.sendBroadcast(intent);
        Log.i(TAG, "Collision settings broadcaster "+key);
    }

    public String getData(String key) {
        if (Stored_settings != null) {
            return Stored_settings.getString(key, "0");

        }
        else
        return "0";

    }


}





