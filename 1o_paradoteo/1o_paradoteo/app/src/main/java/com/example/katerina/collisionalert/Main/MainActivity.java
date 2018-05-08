package com.example.katerina.collisionalert.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.katerina.collisionalert.R;
import com.example.katerina.collisionalert.Sensors.SensorService;
import com.example.katerina.collisionalert.Settings.Collision_Settings;
import com.example.katerina.collisionalert.Settings.SettingsActivity;



public class MainActivity extends AppCompatActivity  {


    private ImageButton PopButton;


    String LIGHT_AVAIL, LIGHT_READ;
    TextView textLIGHT_available, textLIGHT_reading;

    String PROXIMITY_AVAIL, PROXIMITY_READ;
    TextView textPROXIMITY_available, textPROXIMITY_reading;

    String Acc_AVAIL, Acc_READ_X;
    TextView textAcc_available, textAcc_reading_X;
    String Acc_READ_Y;
    TextView textAcc_reading_Y;
    String Acc_READ_Z;
    TextView textAcc_reading_Z;


   // handler for received data from shared preferences
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Collision_Settings.NEW_VALUE_01)) {
                final String param = intent.getStringExtra("value_01");
                textLIGHT_reading.setText("Current value: "+param+" (lux)");
                LIGHT_READ=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_01)) {
                final String param = intent.getStringExtra("availability_01");
                textLIGHT_available.setText(param);
                LIGHT_AVAIL=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_VALUE_02)) {
                final String param = intent.getStringExtra("value_02");
                textPROXIMITY_reading.setText("Current value: "+param+" (cm)");
                PROXIMITY_READ=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_02)) {
                final String param = intent.getStringExtra("availability_02");
                textPROXIMITY_available.setText(param);
                PROXIMITY_AVAIL=param;
            }

            if (intent.getAction().equals(Collision_Settings.NEW_VALUE_04_X)) {
                final String param = intent.getStringExtra("value_04_X");
                textAcc_reading_X.setText("X axis: "+param+" (m/s^2)");
                Acc_READ_X=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_VALUE_04_Y)) {
                final String param = intent.getStringExtra("value_04_Y");
                textAcc_reading_Y.setText("Y axis: "+param+" (m/s^2)");
                Acc_READ_Y=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_VALUE_04_Z)) {
                final String param = intent.getStringExtra("value_04_Z");
                textAcc_reading_Z.setText("Z axis: "+param+" (m/s^2)");
                Acc_READ_Z=param;
            }
            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_04)) {
                final String param = intent.getStringExtra("availability_04");
                textAcc_available.setText(param);
                Acc_AVAIL=param;
            }



        }
    };





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PopButton = (ImageButton) findViewById(R.id.PopButton);
        ImageButton backButton = (ImageButton) findViewById(R.id.BackArrow);

        final Collision_Settings prefs2=Collision_Settings.getInstance(getApplicationContext());


        textLIGHT_available = (TextView) findViewById(R.id.LIGHT_available);
        textLIGHT_reading = (TextView) findViewById(R.id.LIGHT_reading);

        textPROXIMITY_available = (TextView) findViewById(R.id.PROXIMITY_available);
        textPROXIMITY_reading = (TextView) findViewById(R.id.PROXIMITY_reading);

        textAcc_available = (TextView) findViewById(R.id.Acc_available);
        textAcc_reading_X = (TextView) findViewById(R.id.Acc_reading_X);
        textAcc_reading_Y = (TextView) findViewById(R.id.Acc_reading_Y);
        textAcc_reading_Z = (TextView) findViewById(R.id.Acc_reading_Z);



        String avail01 = prefs2.getData("availability_01");
        textLIGHT_available.setText(avail01);
        String read01 =prefs2.getData("value_01");
        textLIGHT_reading.setText("Current value: "+read01+" (lux)");

        String avail02 = prefs2.getData("availability_02");
        textPROXIMITY_available.setText(avail02);
        String read02 =prefs2.getData("value_02");
        textPROXIMITY_reading.setText("Current value: "+read02+" (cm)");

        String avail04 = prefs2.getData("availability_04");
        textAcc_available.setText(avail04);
        String read04x =prefs2.getData("value_04_X");
        textAcc_reading_X.setText("X axis: "+read04x+" (m/s^2)");
        String read04y =prefs2.getData("value_04_Y");
        textAcc_reading_Y.setText("Y axis: "+read04y+" (m/s^2)");
        String read04z =prefs2.getData("value_04_Z");
        textAcc_reading_Z.setText("Z axis: "+read04z+" (m/s^2)");

        View.OnClickListener click_listener_2 = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onBackPressed();
            }



        };

        backButton.setOnClickListener(click_listener_2);



        startService(new Intent(this, SensorService.class));



        // Intent filters

        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(Collision_Settings.NEW_VALUE_01);
        LocalBroadcastManager bm1 = LocalBroadcastManager.getInstance(this);
        bm1.registerReceiver(mBroadcastReceiver, filter1);

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(Collision_Settings.NEW_AVAILABILITY_01);
        LocalBroadcastManager bm2 = LocalBroadcastManager.getInstance(this);
        bm2.registerReceiver(mBroadcastReceiver, filter2);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(Collision_Settings.NEW_VALUE_02);
        LocalBroadcastManager bm3 = LocalBroadcastManager.getInstance(this);
        bm3.registerReceiver(mBroadcastReceiver, filter3);

        IntentFilter filter4 = new IntentFilter();
        filter4.addAction(Collision_Settings.NEW_AVAILABILITY_02);
        LocalBroadcastManager bm4 = LocalBroadcastManager.getInstance(this);
        bm4.registerReceiver(mBroadcastReceiver, filter4);

        IntentFilter filter7x = new IntentFilter();
        filter7x.addAction(Collision_Settings.NEW_VALUE_04_X);
        LocalBroadcastManager bm7x = LocalBroadcastManager.getInstance(this);
        bm7x.registerReceiver(mBroadcastReceiver, filter7x);

        IntentFilter filter7y = new IntentFilter();
        filter7y.addAction(Collision_Settings.NEW_VALUE_04_Y);
        LocalBroadcastManager bm7y = LocalBroadcastManager.getInstance(this);
        bm7y.registerReceiver(mBroadcastReceiver, filter7y);

        IntentFilter filter7z = new IntentFilter();
        filter7z.addAction(Collision_Settings.NEW_VALUE_04_Z);
        LocalBroadcastManager bm7z = LocalBroadcastManager.getInstance(this);
        bm7z.registerReceiver(mBroadcastReceiver, filter7z);

        IntentFilter filter7 = new IntentFilter();
        filter7.addAction(Collision_Settings.NEW_AVAILABILITY_04);
        LocalBroadcastManager bm7 = LocalBroadcastManager.getInstance(this);
        bm7.registerReceiver(mBroadcastReceiver, filter7);












        View.OnClickListener click_listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, PopButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu_main, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_settings:
                                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                                return true;
                            case R.id.action_exit:
                                stopService(new Intent(MainActivity.this, SensorService.class));
                                finish();
                                System.exit(0);
                                return true;


                            default:
                                return false;
                        }
                    }
                });

                popup.show(); //showing popup menu
            }



        };

        PopButton.setOnClickListener(click_listener);

    }

    @Override
    protected void onDestroy() {
       LocalBroadcastManager bm = LocalBroadcastManager.getInstance(this);
        bm.unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        stopService(new Intent(MainActivity.this, SensorService.class));
                        finish();
                        System.exit(0);
                    }
                }).create().show();

    }



}

