package com.example.chapter6_bnvandtl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.chapter6_bnvandtl.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        tabLayout = binding.tabLayout;
        viewPager2 = binding.viewPager;

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        // Fragment 추가하기
        viewPagerAdapter.addFragment(new Fragment1(), "First");
        viewPagerAdapter.addFragment(new Fragment2(), "Second");
        viewPagerAdapter.addFragment(new Fragment3(), "Third");

        viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, poistion) -> {
            tab.setText(viewPagerAdapter.getFragmentTitle(poistion));
        }).attach();

        return binding.getRoot();

    }
}