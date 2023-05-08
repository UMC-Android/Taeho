package com.example.chapter5_memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {
    private EditText memoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        memoEditText = findViewById(R.id.memoEditText);

        Button saveMemoButton = findViewById(R.id.saveButton);
        saveMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 메모 저장 버튼 클릭 시,
                String memo = memoEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("memo", memo);
                setResult(RESULT_OK, resultIntent);

                finish(); // 현재 창 종료하기
            }
        });
    }
}
