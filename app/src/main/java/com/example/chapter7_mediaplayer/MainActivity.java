package com.example.chapter7_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button playButton, pauseButton, stopButton;
    private TextView currentTimeTextView;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        progressBar = findViewById(R.id.progressBar);

        mediaPlayer = MediaPlayer.create(this, R.raw.alanwalker_thedrum);
        handler = new Handler();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    mediaPlayer.start();
                    isPlaying = true;
                    updateProgressBar();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    isPlaying = false;
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alanwalker_thedrum);
                    progressBar.setProgress(0);
                    currentTimeTextView.setText("00:00");
                    isPlaying = false;
                }
            }
        });
    }

    private void updateProgressBar() {
        progressBar.setMax(mediaPlayer.getDuration());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlaying) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    final String currentTime = String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(currentPosition),
                            TimeUnit.MILLISECONDS.toSeconds(currentPosition) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition)));

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mediaPlayer.getCurrentPosition());
                            currentTimeTextView.setText(currentTime);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }
}
