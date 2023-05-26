package com.example.chapter5_memo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {
    private List<MemoEntity> memoList;
    private OnItemClickListener listener;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onFavoriteClick(int position);
        void onLikeClick(int position);
    }

    public MemoAdapter(List<MemoEntity> memoList, OnItemClickListener listener, Context context, RecyclerView recyclerView) {
        this.memoList = memoList;
        this.listener = listener;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.recyclerView = recyclerView;
    }

    public static class MemoViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTextView;
        public ImageView favoriteButton;
        public ImageView likeButton;

        public MemoViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            memoTextView = itemView.findViewById(R.id.memoTextView);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
            likeButton = itemView.findViewById(R.id.likeButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onFavoriteClick(position);
                    }
                }
            });

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onLikeClick(position);
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
        MemoEntity memo = memoList.get(position);
        holder.memoTextView.setText(memo.getContent());

        boolean isFavorite = memo.isFavorite();
        if (isFavorite) {
            holder.favoriteButton.setBackgroundResource(R.drawable.star_yellow);
        } else {
            holder.favoriteButton.setBackgroundResource(R.drawable.star_grey);
        }

        boolean isLiked = memo.isLiked();
        if (isLiked) {
            holder.likeButton.setBackgroundResource(R.drawable.like_blue);
        } else {
            holder.likeButton.setBackgroundResource(R.drawable.like_grey);
        }

        updateFavoriteState(memo, position, holder);
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }


    private void updateFavoriteState(MemoEntity memo, int position, MemoViewHolder holder) {
        boolean isFavorite = memo.isFavorite();

        // SharedPreferences를 사용하여 즐겨찾기 상태를 저장합니다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(String.valueOf(memo.getId()), isFavorite);
        editor.apply();

        // 버튼 이미지를 업데이트합니다.
        if (isFavorite) {
            holder.favoriteButton.setImageResource(R.drawable.star_yellow);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.star_grey);
        }
    }

}
