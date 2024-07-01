package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tiktokapp.R;
import com.example.tiktokapp.fragment.ListVideoFragment;

public class ListSearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_search);
        Fragment fragment = new ListVideoFragment();
        Bundle bundleAnother = getIntent().getExtras();
        String seacrhTitle = bundleAnother.getString("title");
        Bundle bundle = new Bundle();
        bundle.putString("title", seacrhTitle);
        fragment.setArguments(bundle);
        addFragment(fragment);
    }
    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_list_view, fragment);
        fragmentTransaction.commit();
    }
}
