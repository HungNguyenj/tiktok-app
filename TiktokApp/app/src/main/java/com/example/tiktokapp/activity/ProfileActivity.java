package com.example.tiktokapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tiktokapp.R;
import com.example.tiktokapp.fragment.ProfileInfoFragment;
import com.example.tiktokapp.fragment.ProfileLoginFragment;
import com.example.tiktokapp.utils.AuthUtil;

public class ProfileActivity extends BaseActivity {
    private LinearLayout layoutProfile;
    private ImageView btnProfile;
    private ImageView btnHome;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userId = getIntent().getIntExtra("userId", -1);
        initNavbar(this);
        layoutProfile = findViewById(R.id.fragment_layout_profile);
        btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setImageResource(R.drawable.user_fill);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setImageResource(R.drawable.home_outline);
        if (AuthUtil.loggedIn(this)) {
            addFragment(new ProfileInfoFragment());
        } else {
            addFragment(new ProfileLoginFragment());
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt("userId", userId);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout_profile, fragment);
        fragmentTransaction.commit();
    }
}