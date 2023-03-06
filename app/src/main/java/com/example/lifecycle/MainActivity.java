package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;

    private int countLap = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run(){
                int hours = seconds / 3600 % 24;
                int minutes = seconds / 60 % 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
    public void onClickStart(View view) {
        running = true;
    }
    public void onClickStop(View view) {
        running=false;
    }
    public void onClickReset(View view ) {
        countLap=1;
        running=false;
        seconds = 0;
        TextView lapView = (TextView)findViewById(R.id.lap_text);
        lapView.setText("");
    }
    public void onClickLap(View view ) {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        String ActualTime = timeView.getText().toString();

        TextView lapView = (TextView)findViewById(R.id.lap_text);
        String lapActual = lapView.getText().toString();

            if(countLap>=6){
                return;
            }else{
                lapView.setText(lapActual + "\n" + countLap++ + " " + ActualTime);
            }

    }
}