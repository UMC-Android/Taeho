package com.example.chapter3_activityandfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chapter3_activityandfragment.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String text = intent.getStringExtra("텍스트");
        binding.TextView2A.setText(text);

        binding.Button2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);
            }
        });

        binding.ButtonBack.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("BACK_STRING", "Back");
            setResult(RESULT_OK, data);
            finish();
        });
    }
}