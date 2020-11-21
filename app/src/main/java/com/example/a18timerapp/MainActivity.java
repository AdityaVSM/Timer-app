package com.example.a18timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean timerIsActive = false;
    Button goButton;
    CountDownTimer c;
    public void buttonClicked(View view) {

        if (timerIsActive) {
            timerTextView.setText("0:00");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            c.cancel();
            goButton.setText("START!");
            timerIsActive = false;
        } else {

            timerIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP!");
            c = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sirennoise);
                    mediaPlayer.start();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int min = secondsLeft/60;
        int second = secondsLeft - min*60;
        String seconds = Integer.toString(second);
        if(seconds.equals("0")){
            seconds = "00";
        }
        timerTextView.setText(Integer.toString(min)+" : "+seconds);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.SeekBar);
        timerTextView = (TextView)findViewById(R.id.textView);
        goButton = (Button)findViewById(R.id.button);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}