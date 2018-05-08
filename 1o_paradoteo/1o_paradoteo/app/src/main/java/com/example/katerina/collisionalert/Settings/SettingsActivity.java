package com.example.katerina.collisionalert.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.katerina.collisionalert.R;

public class SettingsActivity extends AppCompatActivity {


    // LIGHT SENSOR
    TextView parameter_01;
    SeekBar seek_parameter_01;
    double p1=0;

    TextView parameter_01_time;
    SeekBar seek_parameter_01_time;
    double p1t=0;



    // PROXIMITY SENSOR
    TextView parameter_02;
    SeekBar seek_parameter_02;
    double p2=0;

    TextView parameter_02_time;
    SeekBar seek_parameter_02_time;
    double p2t=0;



    // ACCELERATOR SENSOR
    TextView parameter_04_X;
    SeekBar seek_parameter_04_X;
    double p4_X=0;

    TextView parameter_04_time;
    SeekBar seek_parameter_04_time;
    double p4t=0;

    TextView parameter_04_Y;
    SeekBar seek_parameter_04_Y;
    double p4_Y=0;


    TextView parameter_04_Z;
    SeekBar seek_parameter_04_Z;
    double p4_Z=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageButton backButton = (ImageButton) findViewById(R.id.BackArrow);

        View.OnClickListener click_listener_2 = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SettingsActivity.super.onBackPressed();
            }



        };

        backButton.setOnClickListener(click_listener_2);

        final Collision_Settings prefs2=Collision_Settings.getInstance(getApplicationContext());

        // LIGHT SENSOR
        parameter_01=(TextView) findViewById(R.id.parameter01);
        seek_parameter_01=(SeekBar) findViewById(R.id.seek_parameter01);

        parameter_01_time=(TextView) findViewById(R.id.parameter01_time);
        seek_parameter_01_time=(SeekBar) findViewById(R.id.seek_parameter01_time);

        // PROXIMITY SENSOR
        parameter_02=(TextView) findViewById(R.id.parameter02);
        seek_parameter_02=(SeekBar) findViewById(R.id.seek_parameter02);

        parameter_02_time=(TextView) findViewById(R.id.parameter02_time);
        seek_parameter_02_time=(SeekBar) findViewById(R.id.seek_parameter02_time);



        // ACCELERATOR SENSOR
        parameter_04_X=(TextView) findViewById(R.id.parameter04_X);
        seek_parameter_04_X=(SeekBar) findViewById(R.id.seek_parameter04_X);

        parameter_04_time=(TextView) findViewById(R.id.parameter04_time);
        seek_parameter_04_time=(SeekBar) findViewById(R.id.seek_parameter04_time);

        parameter_04_Y=(TextView) findViewById(R.id.parameter04_Y);
        seek_parameter_04_Y=(SeekBar) findViewById(R.id.seek_parameter04_Y);

        parameter_04_Z=(TextView) findViewById(R.id.parameter04_Z);
        seek_parameter_04_Z=(SeekBar) findViewById(R.id.seek_parameter04_Z);





        // LIGHT SENSOR
        String thres01 = prefs2.getData("threshold_01");
        parameter_01.setText("Threshold for Light Sensor: "+thres01+" (lux)");
        int k1=(int)(Double.parseDouble(thres01)/400);
        seek_parameter_01.setProgress(k1);

        String time01 = prefs2.getData("time_interval_01");
        parameter_01_time.setText("Time interval for Light Sensor: "+time01+" (ms)");
        int k1t=(int)(Double.parseDouble(time01)/50);
        seek_parameter_01_time.setProgress(k1t);




        // PROXIMITY SENSOR
        String thres02 = prefs2.getData("threshold_02");
        parameter_02.setText("Threshold for Proximity Sensor: "+thres02+" (cm)");
        int k2=(int)(Double.parseDouble(thres02)*10);
        seek_parameter_02.setProgress(k2);

        String time02 = prefs2.getData("time_interval_02");
        parameter_02_time.setText("Time interval for Proximity Sensor: "+time02+" (ms)");
        int k2t=(int)(Double.parseDouble(time02)/50);
        seek_parameter_02_time.setProgress(k2t);



        // ACCELERATOR SENSOR
        String thres04_X = prefs2.getData("threshold_04_X");
        parameter_04_X.setText("Threshold for Accelerator Sensor, X axis: "+thres04_X+" (m/s^2)");
        int k4_X=(int)(Double.parseDouble(thres04_X));
        seek_parameter_04_X.setProgress(k4_X);

        String time04 = prefs2.getData("time_interval_04");
        parameter_04_time.setText("Time interval for Accelerator Sensor: "+time04+" (ms)");
        int k4t=(int)(Double.parseDouble(time04)/50);
        seek_parameter_04_time.setProgress(k4t);

        String thres04_Y = prefs2.getData("threshold_04_Y");
        parameter_04_Y.setText("Threshold for Accelerator, Y axis: "+thres04_Y+" (m/s^2)");
        int k4_Y=(int)(Double.parseDouble(thres04_Y));
        seek_parameter_04_Y.setProgress(k4_Y);

        String thres04_Z = prefs2.getData("threshold_04_Z");
        parameter_04_Z.setText("Threshold for Accelerator Sensor, Z axis: "+thres04_Z+" (m/s^2)");
        int k4_Z=(int)(Double.parseDouble(thres04_Z));
        seek_parameter_04_Z.setProgress(k4_Z);





        // LIGHT SENSOR
        seek_parameter_01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p1 = (progress*400);
                parameter_01.setText("Threshold for Light Sensor: "+String.valueOf(p1)+" (lux)");
                prefs2.saveData("threshold_01",String.valueOf(p1));

            }

        } );

        seek_parameter_01_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p1t = (progress*50);
                parameter_01_time.setText("Time interval for Light Sensor: "+String.valueOf(p1t)+" (ms)");
                prefs2.saveData("time_interval_01",String.valueOf(p1t));

            }

        } );


        // PROXIMITY SENSOR
        seek_parameter_02.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p2 = (progress/10);
                parameter_02.setText("Threshold for Proximity Sensor: "+String.valueOf(p2)+" (cm)");
                prefs2.saveData("threshold_02",String.valueOf(p2));

            }

        } );

        seek_parameter_02_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p2t = (progress*50);
                parameter_02_time.setText("Time interval for Proximity Sensor: "+String.valueOf(p2t)+" (ms)");
                prefs2.saveData("time_interval_02",String.valueOf(p2t));

            }

        } );







        // ACCELERATOR SENSOR
        seek_parameter_04_X.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p4_X = (progress);
                parameter_04_X.setText("Threshold for Accelerator Sensor, X axis: "+String.valueOf(p4_X)+" (m/s^2)");
                prefs2.saveData("threshold_04_X",String.valueOf(p4_X));

            }

        } );

        seek_parameter_04_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p4t = (progress*50);
                parameter_04_time.setText("Time interval for Accelerator Sensor: "+String.valueOf(p4t)+" (ms)");
                prefs2.saveData("time_interval_04",String.valueOf(p4t));

            }

        } );



        seek_parameter_04_Y.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p4_Y = (progress);
                parameter_04_Y.setText("Threshold for Accelerator Sensor, Y axis: "+String.valueOf(p4_Y)+" (m/s^2)");
                prefs2.saveData("threshold_04_Y",String.valueOf(p4_Y));

            }

        } );



        seek_parameter_04_Z.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p4_Z = (progress);
                parameter_04_Z.setText("Threshold for Accelerator Sensor, Z axis: "+String.valueOf(p4_Z)+" (m/s^2)");
                prefs2.saveData("threshold_04_Z",String.valueOf(p4_Z));

            }

        } );


    }


}
