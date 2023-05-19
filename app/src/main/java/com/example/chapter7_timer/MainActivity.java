package com.example.chapter7_timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter7_timer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button startButton, stopButton, pauseButton, minUp, minDown, secUp, secDown;
    private Handler timeHandler;
    private Thread timeThread;
    private TextView timeTextView;
    private boolean isTimerRunning = false;
    private boolean isTimerFinished = false;
    private boolean isTimerPaused = false;
    private SoundPool soundPool;
    private int soundId;
    private int setTime = 0;
    private int timeRemaining = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        timeTextView = binding.time;
        startButton = binding.startBtn;
        stopButton = binding.stopBtn;
        pauseButton = binding.pauseBtn;

        minUp = binding.minUp;
        minDown = binding.minDown;
        secUp = binding.secUp;
        secDown = binding.secDown;

        soundPool = new SoundPool.Builder().build();
        soundId = soundPool.load(this, R.raw.notification_sound, 1);

        timeHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                timeRemaining = msg.arg1;
                updateTimer(timeRemaining);
            }
        };

        startButton.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 이벤트
            @Override
            public void onClick(View view) {
                if(!(setTime == 0)) startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 이벤트
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 이벤트
            @Override
            public void onClick(View view) {
                if (isTimerPaused) {
                    resumeTimer();
                } else {
                    pauseTimer();
                }
            }
        });

        minUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setTime >= 5940) setTime = 5995;
                else setTime += 60;
                changeTimer(setTime);
            }
        });

        minDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setTime >= 60) setTime -= 60;
                else setTime = 0;
                changeTimer(setTime);
            }
        });

        secUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setTime >= 5995) setTime = 5995;
                else setTime += 5;
                changeTimer(setTime);
            }
        });

        secDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setTime >= 5) setTime -= 5;
                else setTime = 0;
                changeTimer(setTime);
            }
        });
    }

    private void startTimer() { // 타이머 시작
        isTimerRunning = true;
        isTimerFinished = false;
        startButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        minUp.setVisibility(View.GONE);
        minDown.setVisibility(View.GONE);
        secUp.setVisibility(View.GONE);
        secDown.setVisibility(View.GONE);

        if (timeThread != null && timeThread.isAlive()) {
            // 이미 실행 중인 타이머 스레드가 있을 경우 중복 실행을 방지하기 위해 종료시킴
            timeThread.interrupt();
        }

        timeRemaining = setTime;

        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeRemaining > 0 && isTimerRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return; // 스레드가 interrupt 되면 종료
                    }
                    timeRemaining--;
                    setTime = timeRemaining;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                updateTimer(timeRemaining);
                            }
                    });
                }

                if (timeRemaining <= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timerComplete();
                        }
                    });
                }
            }
        });
        timeThread.start();
    }

    private void stopTimer() { // 타이머 초기화
        isTimerRunning = false;
        isTimerFinished = true;
        minUp.setVisibility(View.VISIBLE);
        minDown.setVisibility(View.VISIBLE);
        secUp.setVisibility(View.VISIBLE);
        secDown.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.GONE);
        setTime = 0;
        timeHandler.removeCallbacksAndMessages(null);
        timeTextView.setText("00 : 00");
    }

    private void pauseTimer() { // 타이머 일시 정지
        isTimerPaused = true;
        isTimerRunning = false;
        pauseButton.setText("CONTINUE");
        minUp.setVisibility(View.VISIBLE);
        minDown.setVisibility(View.VISIBLE);
        secUp.setVisibility(View.VISIBLE);
        secDown.setVisibility(View.VISIBLE);
        pauseButton.setBackgroundResource(R.drawable.continue_button);
        pauseButton.setTextColor(Color.parseColor("#444444"));
    }

    private void resumeTimer() { // 일시 정지된 타이머 재개
        isTimerPaused = false;
        isTimerRunning = true;
        pauseButton.setText("PAUSE");
        minUp.setVisibility(View.GONE);
        minDown.setVisibility(View.GONE);
        secUp.setVisibility(View.GONE);
        secDown.setVisibility(View.GONE);
        pauseButton.setBackgroundResource(R.drawable.pause_button);
        pauseButton.setTextColor(ContextCompat.getColor(this, R.color.white));

        timeRemaining = setTime;

        if (timeThread != null && timeThread.isAlive()) {
            // 이미 실행 중인 타이머 스레드가 있을 경우 중복 실행을 방지하기 위해 종료시킴
            timeThread.interrupt();
        }

        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeRemaining > 0 && isTimerRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return; // 스레드가 interrupt 되면 종료
                    }

                    if (!isTimerPaused) {
                        timeRemaining--;
                        setTime = timeRemaining;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTimer(timeRemaining);
                            }
                        });
                    }
                }

                if (timeRemaining <= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timerComplete();
                        }
                    });
                }
            }
        });
        timeThread.start();
    }

    private void updateTimer(int timeRemaining) { // 타이머 시간 업데이트
        if(isTimerFinished || isTimerPaused) return;

        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String timeString = String.format("%02d : %02d", minutes, seconds);
        timeTextView.setText(timeString);
    }

    private void changeTimer(int setTime) {
        int minutes = setTime / 60;
        int seconds = setTime % 60;
        String timeString = String.format("%02d : %02d", minutes, seconds);
        timeTextView.setText(timeString);
    }

    private void timerComplete() {
        isTimerRunning = false;
        startButton.setText("START");
        timeTextView.setText("타이머 종료");
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}