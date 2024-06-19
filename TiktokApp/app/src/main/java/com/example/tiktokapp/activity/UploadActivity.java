package com.example.tiktokapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.UploadTabAdapter;
import com.google.android.material.tabs.TabLayout;

public class UploadActivity extends AppCompatActivity {
    private ImageView btnClose;
    private ViewPager viewPager;
    private UploadTabAdapter uploadtabAdapter;
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
        uploadtabAdapter = new UploadTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(uploadtabAdapter);
        viewPager.setCurrentItem(0);
        tabLayout = findViewById(R.id.tabViewFiles);

    }
    private void handleAction() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                Toast.makeText(UploadActivity.this, "Your message here", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse);
        finish();
    }
}