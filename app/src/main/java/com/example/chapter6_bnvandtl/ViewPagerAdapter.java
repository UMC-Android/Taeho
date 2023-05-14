package com.example.chapter6_bnvandtl;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitle = new ArrayList<>();

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitle.add(title);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public String getFragmentTitle(int position) {
        return fragmentTitle.get(position);
    }
}
