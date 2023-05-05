package com.example.chapter4_memo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter4_memo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public String memo = "";


    private static final String KEY_MEMO = "memo";
    private static final String KEY_MEMO_BYTE = "memo_byte";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button confirmButton = binding.buttonConfirm;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo = binding.editTextMemo.getText().toString();
                Intent intent = new Intent(MainActivity.this, ConfirmActivity.class);
                intent.putExtra("memo", memo);
                intent.putExtra("memo_byte", memo.getBytes());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (memo != null) {
            binding.editTextMemo.setText(memo);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        memo = binding.editTextMemo.getText().toString();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to rewrite your memo?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        memo = "";
                        binding.editTextMemo.setText("");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Nothing happens
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}