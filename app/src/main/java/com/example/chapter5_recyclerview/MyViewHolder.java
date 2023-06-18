package com.example.chapter5_recyclerview;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public Switch Switchview;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView1);
    }

    public Switch getSwitch() {
        Switchview = itemView.findViewById(R.id.switch1);
        return Switchview;
    }
}
