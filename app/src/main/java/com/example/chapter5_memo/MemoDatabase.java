package com.example.chapter5_memo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MemoEntity.class}, version = 2)
@TypeConverters(Converters.class)
public abstract class MemoDatabase extends RoomDatabase {

    private static MemoDatabase instance;

    public abstract MemoDao memoDao();

    public static synchronized MemoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MemoDatabase.class, "memo_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}