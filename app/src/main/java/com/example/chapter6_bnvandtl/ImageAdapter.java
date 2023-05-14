package com.example.chapter6_bnvandtl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private final List<Integer> images;
    private final LayoutInflater inflater;

    public ImageAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    public void addImage(int image) {
        images.add(image);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}