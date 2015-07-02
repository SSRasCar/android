package com.example.summerseed.carcontroller;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {

    TextView textView;
    StringBuilder builder = new StringBuilder();

    float [] history = new float[2];
    String [] direction = {"NONE","NONE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b_forward = (Button) findViewById(R.id.button_forward);
        Button b_back = (Button) findViewById(R.id.button_back);
        Button b_left = (Button) findViewById(R.id.button_left);
        Button b_right = (Button) findViewById(R.id.button_right);

        textView = new TextView(this);


        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

        b_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buttonFunctions(builder.toString());
                buttonFunctions("Forward");
            }
        });
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFunctions("Back");
            }
        });
        b_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFunctions("Left");
            }
        });
        b_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFunctions("Right");
            }
        });

    }

    private void buttonFunctions(String button) {
       //Toast toast = Toast.makeText(this, button ,Toast.LENGTH_SHORT);
       //toast.show();
        final Toast toast = Toast.makeText(getApplicationContext(), button, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];

        history[0] = event.values[0];
        history[1] = event.values[1];

        if (xChange > 2){
            direction[0] = "LEFT";
        }
        else if (xChange < -2){
            direction[0] = "RIGHT";
        }

        if (yChange > 2){
            direction[1] = "DOWN";
        }
        else if (yChange < -2){
            direction[1] = "UP";
        }

        builder.setLength(0);
        builder.append("x: ");
        builder.append(direction[0]);
        builder.append(" y: ");
        builder.append(direction[1]);

        textView.setText(builder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
