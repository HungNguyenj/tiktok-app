package com.example.tiktokapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tiktokapp.fragment.UploadFileFragment;

public class UploadTabAdapter extends FragmentPagerAdapter {
    public UploadTabAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public UploadTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new UploadFileFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
