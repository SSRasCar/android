package com.example.summerseed.seekbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import widget.VerticalSeekBar;


public class MainActivity extends ActionBarActivity{
    VerticalSeekBar forwardBack;
    SeekBar leftRight;
    TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forwardBack = (VerticalSeekBar) findViewById(R.id.seekBarFB);
        leftRight = (SeekBar) findViewById(R.id.seekBarLR);
        final Button reset = (Button) findViewById(R.id.reset);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        reset.setVisibility(View.INVISIBLE);
        //final TextView tv = (TextView) findViewById(R.id.textView);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        forwardBack.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                makeMove(progress,1);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                forwardBack.setProgress(50);
                forwardBack.updateThumb();
            }
        });
        leftRight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                makeMove(progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                leftRight.setProgress(50);
            }
        });
    }

    private  void reset(){
        forwardBack.setProgress(50);
        leftRight.setProgress(50);
        forwardBack.updateThumb();

    }
    private void makeMove(int progress, int i) {
        switch (i){
            case 0://left right movement
                if(progress==50){
                    textView.setText("Stay");
                }else if(progress<50){
                    //moveLeft();
                    textView.setText("Move Left"+progress);
                }else{
                    textView.setText("Move Right"+progress);
                    //moveRight();
                }
                break;
            case 1://forward back movement
                if(progress==50){
                    textView2.setText("Stay");
                }else if(progress<50){
                    textView2.setText("Move Back:"+progress);
                    //moveBack();
                }else{
                    textView2.setText("Move Forward"+progress);
                    //moveForward();
                }
                break;
        }
    }
}
