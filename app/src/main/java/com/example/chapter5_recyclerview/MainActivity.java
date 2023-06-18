package com.example.chapter5_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> itemList = new ArrayList<>();
        itemList.add("바나나");
        itemList.add("사과");
        itemList.add("포도");
        itemList.add("오렌지");
        itemList.add("카람볼라");
        itemList.add("망고스틴");
        itemList.add("잭푸르츠");
        itemList.add("람부탄");
        itemList.add("블루베리");
        itemList.add("감");
        itemList.add("참외");
        itemList.add("코코넛");
        itemList.add("귤");
        itemList.add("딸기");
        itemList.add("수박");

        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}