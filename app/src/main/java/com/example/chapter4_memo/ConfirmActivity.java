package com.example.chapter4_memo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chapter4_memo.databinding.ActivityConfirmBinding;

public class ConfirmActivity extends AppCompatActivity {
    private ActivityConfirmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String memo = getIntent().getStringExtra("memo");
        binding.textViewConfirm.setText(memo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String memo = getIntent().getStringExtra("memo");
        int byteCount = memo.getBytes().length;
        String byteString = "Byte count: " + byteCount;
        binding.textViewByteCount.setText(byteString);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String memo = getIntent().getStringExtra("memo");
        binding.textViewConfirm.setText(memo);
    }
}