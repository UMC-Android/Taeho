package com.example.chapter5_memo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> memoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MemoAdapter adapter;

    private static final int ADD_MEMO = 1; // 메모 저장 시 목록 반영

    ActivityResultLauncher<Intent> memoLauncher = registerForActivityResult( // Activity 돌아갈 시, 메모 목록 갱신하기
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String memo = data.getStringExtra("memo");
                        memoList.add(memo);
                        adapter.notifyItemInserted(memoList.size() - 1);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 생성 시
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.memoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MemoAdapter(memoList, new MemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) { // 메모 목록 클릭 했을 시, 메모 삭제하기
                memoList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        recyclerView.setAdapter(adapter);

        Button addMemoButton = findViewById(R.id.addButton);
        addMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 메모 추가 버튼 클릭 했을 시, MemoActivity 실행(메모 적는 창 띄우기)
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                memoLauncher.launch(intent);
            }
        });;
    }
}