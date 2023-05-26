package com.example.chapter5_memo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemoViewHolder extends RecyclerView.ViewHolder {
    public TextView memoTextView;
    public Button favoriteButton;

    public MemoViewHolder(View itemView, final MemoAdapter.OnItemClickListener listener) {
        super(itemView);
        memoTextView = itemView.findViewById(R.id.memoTextView);
        favoriteButton = itemView.findViewById(R.id.favoriteButton);

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

    }
}