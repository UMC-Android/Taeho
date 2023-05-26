package com.example.chapter5_memo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MemoViewModel extends AndroidViewModel {

    private MemoRepository memoRepository;
    private LiveData<List<MemoEntity>> allMemos;

    public MemoViewModel(Application application) {
        super(application);
        memoRepository = new MemoRepository(application);
        allMemos = memoRepository.getAllMemos();
    }

    public void insert(MemoEntity memo) {
        memoRepository.insert(memo);
    }

    public void update(MemoEntity memo) {
        memoRepository.update(memo);
    }

    public void delete(MemoEntity memo) {
        memoRepository.delete(memo);
    }

    public LiveData<List<MemoEntity>> getAllMemos() {
        return allMemos;
    }

}
