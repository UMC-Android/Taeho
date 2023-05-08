package com.example.chapter5_memo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {
    private List<String> memoList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MemoAdapter(List<String> memoList, OnItemClickListener listener) {
        this.memoList = memoList;
        this.listener = listener;
    }

    public static class MemoViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTextView;

        public MemoViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            memoTextView = itemView.findViewById(R.id.memoTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        MemoViewHolder viewHolder = new MemoViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        String memo = memoList.get(position);
        holder.memoTextView.setText(memo);
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }
}
