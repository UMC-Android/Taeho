package com.example.chapter5_memo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoRepository {

    private MemoDao memoDao;
    private LiveData<List<MemoEntity>> allMemos;
    private ExecutorService executor;

    public MemoRepository(Application application) {
        MemoDatabase database = MemoDatabase.getInstance(application);
        memoDao = database.memoDao();
        allMemos = memoDao.getAllMemos();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(MemoEntity memo) {
        executor.execute(() -> memoDao.insert(memo));
    }

    public void update(MemoEntity memo) {
        executor.execute(() -> memoDao.update(memo));
    }

    public void delete(MemoEntity memo) {
        executor.execute(() -> memoDao.delete(memo));
    }

    public LiveData<List<MemoEntity>> getAllMemos() {
        return allMemos;
    }

}
