package com.example.tiktokapp.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.FragmentViewPaggerAdapter;
import com.example.tiktokapp.fragment.ImageFilesFragment;
import com.example.tiktokapp.fragment.VideoAndImageFilesFragment;
import com.example.tiktokapp.fragment.VideoFilesFragment;
import com.google.android.material.tabs.TabLayout;

public class UploadActivity extends AppCompatActivity {
    private ImageView btnClose;
    private ViewPager viewPager;
    private FragmentViewPaggerAdapter uploadtabAdapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        handleAction();
    }
    private void init() {
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            onBackPressed();
        });
        viewPager = findViewById(R.id.viewPagger);
        uploadtabAdapter = new FragmentViewPaggerAdapter(getSupportFragmentManager());
        uploadtabAdapter.addFragment(new VideoAndImageFilesFragment(), "All");
        uploadtabAdapter.addFragment(new VideoFilesFragment(), "Video");
        uploadtabAdapter.addFragment(new ImageFilesFragment(), "Image");
        viewPager.setAdapter(uploadtabAdapter);
        tabLayout = findViewById(R.id.tabViewFiles);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void handleAction() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse);
        finish();
    }
}