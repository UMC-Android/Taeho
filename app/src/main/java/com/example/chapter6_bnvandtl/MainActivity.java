package com.example.chapter6_bnvandtl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chapter6_bnvandtl.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new HomeFragment())
                .commit();

        NavigationBarView navigationBarView = binding.bottomNavi;
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.Camera:
                        selectedFragment = new CameraFragment();
                        break;
                    case R.id.Home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.Picture:
                        selectedFragment = new PictureFragment();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        // 기본으로 선택할 메뉴 ID를 전달하여 설정
        navigationBarView.setSelectedItemId(R.id.Home);
    }
}