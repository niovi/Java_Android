package com.example.katerina.collisionalert;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import static android.os.SystemClock.sleep;
import static android.widget.Toast.makeText;


public class Notify {

    private Toast toast;

    private static final String TAG = "Notify";


    private void post_alert(final int time){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    int play_time=time;
                    while (play_time>0) {
                        final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 150);
                        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                        tg.release();
                        toast.show();
                        play_time=play_time-150;
                        //Log.i(TAG, "beep beep beep beep");
                        sleep(150);
                        toast.cancel();
                    }

                } catch (Exception e) {
                    Log.i(TAG,"Exception: ",e);
                }

            }
        }).start();


    }



    public Notify(final Context context, int time_interval) {


        Handler handler = new Handler(Looper.getMainLooper());
        Runnable show_run = new Runnable(){
            @SuppressLint("ShowToast")
            public  void run(){
                toast = makeText(context, "Collision Alert!!!", Toast.LENGTH_SHORT);
                Log.i(TAG,"Inside runnable for toast.makeText");
            }
        };
        handler.post(show_run);
        post_alert(time_interval);




    }







}








