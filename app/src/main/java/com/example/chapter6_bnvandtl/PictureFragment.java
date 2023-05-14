package com.example.chapter6_bnvandtl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter6_bnvandtl.databinding.FragmentPictureBinding;

import me.relex.circleindicator.CircleIndicator3;

public class PictureFragment extends Fragment {

    FragmentPictureBinding binding;

    ViewPager2 viewPager2;
    CircleIndicator3 circleIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPictureBinding.inflate(getLayoutInflater());

        viewPager2 = binding.viewPager;
        circleIndicator = binding.indicator;

        ImageAdapter imageAdapter = new ImageAdapter(inflater.getContext());

        imageAdapter.addImage(R.drawable.alrescha123);
        imageAdapter.addImage(R.drawable.ngc_602_123);
        imageAdapter.addImage(R.drawable.ngc_602_done_123);

        viewPager2.setAdapter(imageAdapter);

        circleIndicator.setViewPager(viewPager2);

        return binding.getRoot();
    }
}