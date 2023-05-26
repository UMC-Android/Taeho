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

    @Query("UPDATE memo_table SET is_liked = NOT is_liked WHERE id = :memoId")
    void toggleLikedState(long memoId);

    @Query("SELECT * FROM memo_table WHERE is_favorite = 1")
    LiveData<List<MemoEntity>> getFavoriteMemos();
}
