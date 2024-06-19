package com.example.tiktokapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.utils.IntentUtil;

public class HomeActivity extends AppCompatActivity {
    private ImageView soundDisk;
    private ImageView uploadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    private void init(){
        //        Run sound disk
        soundDisk = findViewById(R.id.soundDisk);
        Glide.with(this).load(R.drawable.disk).into(soundDisk);
        uploadButton = findViewById(R.id.btnUpload);
        uploadButton.setOnClickListener(v -> {
            IntentUtil.changeActivity(this, UploadActivity.class);
            overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
        });
    }

}