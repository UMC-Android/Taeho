package com.example.chapter6_bnvandtl;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
    }

    public void bind(int image) {
        imageView.setImageResource(image);
    }
}