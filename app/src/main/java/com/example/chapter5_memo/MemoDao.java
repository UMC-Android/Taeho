package com.example.chapter5_memo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {

    @Insert
    void insert(MemoEntity memo);

    @Update
    void update(MemoEntity memo);

    @Delete
    void delete(MemoEntity memo);

    @Query("SELECT * FROM memo_table ORDER BY timestamp DESC")
    LiveData<List<MemoEntity>> getAllMemos();
}
