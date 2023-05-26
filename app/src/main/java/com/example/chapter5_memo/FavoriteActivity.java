package com.example.chapter5_memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private List<MemoEntity> favoriteMemoList;
    private RecyclerView recyclerView;
    private MemoAdapter adapter;
    private MemoRepository memoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.favoriteMemoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteMemoList = new ArrayList<>();
        memoRepository = new MemoRepository(getApplication());

        // 즐겨찾기된 메모 목록을 가져오는 LiveData를 관찰합니다.
        LiveData<List<MemoEntity>> favoriteMemoListData = memoRepository.getFavoriteMemos();

        favoriteMemoListData.observe(this, new Observer<List<MemoEntity>>() {
            @Override
            public void onChanged(List<MemoEntity> memos) {
                favoriteMemoList.clear();
                favoriteMemoList.addAll(memos);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new MemoAdapter(favoriteMemoList, new MemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 메모 클릭 시 동작
                // 예: 메모 내용 보기 등
            }

            @Override
            public void onFavoriteClick(int position) {
                // 즐겨찾기 버튼 클릭 시 동작
                // 예: 즐겨찾기 상태 변경 등
            }

            @Override
            public void onLikeClick(int position) {
                // 좋아요 버튼 클릭 시 동작
                // 예: 좋아요 상태 변경 등
            }
        }, this, recyclerView);

        recyclerView.setAdapter(adapter);
    }
}