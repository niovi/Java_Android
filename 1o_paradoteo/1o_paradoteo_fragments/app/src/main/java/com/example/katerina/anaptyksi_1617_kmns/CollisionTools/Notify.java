package com.example.katerina.anaptyksi_1617_kmns.CollisionTools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import static android.os.SystemClock.sleep;


public class Notify {
    private Boolean danger,danger2;
    private Toast toast;
    private static final String TAG = "Notify";
    Collision_Settings set_notify;

    private void post_alert(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (danger) {
                        final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 150);
                        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                        toast.show();
                        Log.i(TAG, "beep beep beep beep");
                        sleep(150);
                        tg.release();
                        toast.cancel();

                    }

                } catch (Exception e) {
                    Log.i(TAG,"Exception: ",e);
                }

            }
        }).start();


    }


    Notify(final Context context) {
        Log.i(TAG,"Notify created");
        set_notify=Collision_Settings.getInstance(context);
        danger2=danger=Boolean.getBoolean(set_notify.getData("danger_global"));

        Handler handler = new Handler(Looper.getMainLooper());
        Runnable show_run = new Runnable(){
            public  void run(){
                toast = Toast.makeText(context, "Collision Alert!!!", Toast.LENGTH_SHORT);
                Log.i(TAG,"Inside runnable for toast.makeText");
            }
        };
        handler.post(show_run);
        post_alert();







        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(Collision_Settings.GLOBAL_DANGER);
        LocalBroadcastManager bm2 = LocalBroadcastManager.getInstance(context);


        BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Collision_Settings.GLOBAL_DANGER)) {
                    final String param = intent.getStringExtra("danger_global");
                    danger2=danger = param.equals(String.valueOf(true));
                    Log.i(TAG,"Notified for new danger");
                    post_alert();
                }

            }
        };
        bm2.registerReceiver(mBroadcastReceiver, filter2);


    }




}

