package com.example.tiktokapp.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tiktokapp.R;
import com.example.tiktokapp.fragment.ProfileInfoFragment;
import com.example.tiktokapp.fragment.ProfileLoginFragment;
import com.example.tiktokapp.utils.Auth;

public class Profile extends AppCompatActivity {
    private LinearLayout layoutProfile;
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
        layoutProfile = findViewById(R.id.fragment_layout_profile);
        if (Auth.getCurrentUser() != null) {
            addFragment(new ProfileInfoFragment());
        } else {
            addFragment(new ProfileLoginFragment());
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_list_view, fragment);
        fragmentTransaction.commit();
    }
}