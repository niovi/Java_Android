package com.example.katerina.anaptyksi_1617_kmns.Activities;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.katerina.anaptyksi_1617_kmns.AcceleratorSensorTools.AcceleratorFragment;
import com.example.katerina.anaptyksi_1617_kmns.CollisionTools.Collision_Settings;
import com.example.katerina.anaptyksi_1617_kmns.GPSTools.GPSFragment;
import com.example.katerina.anaptyksi_1617_kmns.GyroscopeSensorTools.GyroscopeFragment;
import com.example.katerina.anaptyksi_1617_kmns.LightSensorTools.LightFragment;
import com.example.katerina.anaptyksi_1617_kmns.LinearAcceleratorSensorTools.LinearAcceleratorFragment;
import com.example.katerina.anaptyksi_1617_kmns.ProximitySensorTools.ProximityFragment;
import com.example.katerina.anaptyksi_1617_kmns.R;
import com.example.katerina.anaptyksi_1617_kmns.CollisionTools.SensorService;

import static com.example.katerina.anaptyksi_1617_kmns.AcceleratorSensorTools.Accelerator_Sensor.flag_accel_available;
import static com.example.katerina.anaptyksi_1617_kmns.AcceleratorSensorTools.Accelerator_Sensor.flag_accel_not_available;
import static com.example.katerina.anaptyksi_1617_kmns.GPSTools.GPS_Sensor.flag_gps_available;
import static com.example.katerina.anaptyksi_1617_kmns.GPSTools.GPS_Sensor.flag_gps_not_available;
import static com.example.katerina.anaptyksi_1617_kmns.GyroscopeSensorTools.Gyroscope_Sensor.flag_gyro_available;
import static com.example.katerina.anaptyksi_1617_kmns.GyroscopeSensorTools.Gyroscope_Sensor.flag_gyro_not_available;
import static com.example.katerina.anaptyksi_1617_kmns.LightSensorTools.Light_Sensor.flag_light_available;
import static com.example.katerina.anaptyksi_1617_kmns.LightSensorTools.Light_Sensor.flag_light_not_available;
import static com.example.katerina.anaptyksi_1617_kmns.LinearAcceleratorSensorTools.Linear_Accelerator_Sensor.flag_linear_available;
import static com.example.katerina.anaptyksi_1617_kmns.LinearAcceleratorSensorTools.Linear_Accelerator_Sensor.flag_linear_not_available;
import static com.example.katerina.anaptyksi_1617_kmns.ProximitySensorTools.Proximity_Sensor.flag_proximity_available;
import static com.example.katerina.anaptyksi_1617_kmns.ProximitySensorTools.Proximity_Sensor.flag_proximity_not_available;


public class MainActivity extends AppCompatActivity {

    private ImageButton PopButton;
    private ImageButton BackButton;

    private static final String TAG = "Main Activity";


    String LIGHT_AVAIL;
    TextView textLIGHT_available;

    String PROXIMITY_AVAIL;
    TextView textPROXIMITY_available;

    String GYROSCOPE_AVAIL;
    TextView textGYROSCOPE_available;

    String LINEAR_AVAIL;
    TextView textLINEAR_available;

    String ACCEL_AVAIL;
    TextView textACCEL_available;

    String GPS_AVAIL;
    TextView textGPS_available;


    // handler for received data from service
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_01)) {
                final String param = intent.getStringExtra("availability_01");
                textLIGHT_available.setText(param);
                LIGHT_AVAIL = param;
                set_frag_view();
            }

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_02)) {
                final String param = intent.getStringExtra("availability_02");
                textPROXIMITY_available.setText(param);
                PROXIMITY_AVAIL = param;
                set_frag_view();
            }

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_03)) {
                final String param = intent.getStringExtra("availability_03");
                textGYROSCOPE_available.setText(param);
                GYROSCOPE_AVAIL = param;
                set_frag_view();
            }

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_04)) {
                final String param = intent.getStringExtra("availability_04");
                textLINEAR_available.setText(param);
                LINEAR_AVAIL = param;
                set_frag_view();
            }

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_05)) {
                final String param = intent.getStringExtra("availability_05");
                textACCEL_available.setText(param);
                ACCEL_AVAIL = param;
                set_frag_view();
            }

            if (intent.getAction().equals(Collision_Settings.NEW_AVAILABILITY_06)) {
                final String param = intent.getStringExtra("availability_06");
                textGPS_available.setText(param);
                GPS_AVAIL = param;
                set_frag_view();
            }
        }
    };


    private void set_filters(){


        // Intent filters
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(Collision_Settings.NEW_AVAILABILITY_01);
        LocalBroadcastManager bm2 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm2.registerReceiver(mBroadcastReceiver, filter2);

        IntentFilter filter4 = new IntentFilter();
        filter4.addAction(Collision_Settings.NEW_AVAILABILITY_02);
        LocalBroadcastManager bm4 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm4.registerReceiver(mBroadcastReceiver, filter4);

        IntentFilter filter6 = new IntentFilter();
        filter6.addAction(Collision_Settings.NEW_AVAILABILITY_03);
        LocalBroadcastManager bm6 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm6.registerReceiver(mBroadcastReceiver, filter6);

        IntentFilter filter7 = new IntentFilter();
        filter7.addAction(Collision_Settings.NEW_AVAILABILITY_04);
        LocalBroadcastManager bm7 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm7.registerReceiver(mBroadcastReceiver, filter7);

        IntentFilter filter8 = new IntentFilter();
        filter8.addAction(Collision_Settings.NEW_AVAILABILITY_05);
        LocalBroadcastManager bm8 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm8.registerReceiver(mBroadcastReceiver, filter8);

        IntentFilter filter9 = new IntentFilter();
        filter9.addAction(Collision_Settings.NEW_AVAILABILITY_06);
        LocalBroadcastManager bm9 = LocalBroadcastManager.getInstance(getApplicationContext());
        bm9.registerReceiver(mBroadcastReceiver, filter9);



    }


    private void set_frag_view(){

        final Collision_Settings prefs2=Collision_Settings.getInstance(getApplicationContext());


        String avail01=flag_light_not_available;
        avail01 = prefs2.getData("availability_01");
        textLIGHT_available.setText(avail01);
        String avail02=flag_proximity_not_available;
        avail02 = prefs2.getData("availability_02");
        textPROXIMITY_available.setText(avail02);
        String avail03=flag_gyro_not_available;
        avail03 = prefs2.getData("availability_03");
        textGYROSCOPE_available.setText(avail03);
        String avail04=flag_linear_not_available;
        avail04 = prefs2.getData("availability_04");
        textLINEAR_available.setText(avail04);
        String avail05=flag_accel_not_available;
        avail05 = prefs2.getData("availability_05");
        textACCEL_available.setText(avail05);
        String avail06=flag_gps_not_available;
        avail06 = prefs2.getData("availability_06");
        textGPS_available.setText(avail06);



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();


        LightFragment lightS = new LightFragment();
        ProximityFragment proximityS = new ProximityFragment();
        GyroscopeFragment gyroscopeS = new GyroscopeFragment();
        LinearAcceleratorFragment linearS = new LinearAcceleratorFragment();
        AcceleratorFragment accelS = new AcceleratorFragment();
        GPSFragment gpsS = new GPSFragment();


        if((avail01.equals(flag_light_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_01, lightS, "LIGHT");
        }else fragmentTransaction.remove(lightS);


        if((avail02.equals(flag_proximity_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_02, proximityS, "PROXIMITY");
        }else fragmentTransaction.remove(proximityS);

        if((avail03.equals(flag_gyro_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_03, gyroscopeS, "GYROSCOPE");
        }else fragmentTransaction.remove(gyroscopeS);

        if((avail04.equals(flag_linear_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_04, linearS, "LINEAR ACCELERATOR");
        }else fragmentTransaction.remove(linearS);

        if((avail05.equals(flag_accel_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_05, accelS, "ACCELERATOR");
        }else fragmentTransaction.remove(accelS);

        if((avail06.equals(flag_gps_available)))
        {
            fragmentTransaction.replace(R.id.fragment_container_06, gpsS, "LOCATION");
        }else fragmentTransaction.remove(gpsS);

        fragmentTransaction.commit();



    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        startService(new Intent(this, SensorService.class));
        set_filters();
        setContentView(R.layout.activity_main);
        BackButton = (ImageButton) findViewById(R.id.BackArrow);
        PopButton = (ImageButton) findViewById(R.id.PopButton);


        textLIGHT_available = (TextView) findViewById(R.id.LIGHT_available);
        textPROXIMITY_available = (TextView) findViewById(R.id.PROXIMITY_available);
        textGYROSCOPE_available = (TextView) findViewById(R.id.GYROSCOPE_available);
        textLINEAR_available = (TextView) findViewById(R.id.LINEAR_available);
        textACCEL_available = (TextView) findViewById(R.id.ACCEL_available);
        textGPS_available = (TextView) findViewById(R.id.GPS_available);

        set_frag_view();




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

        View.OnClickListener click_listener_2 = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onBackPressed();
            }



        };

        BackButton.setOnClickListener(click_listener_2);






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

