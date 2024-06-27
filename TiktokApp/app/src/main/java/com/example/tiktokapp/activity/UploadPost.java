package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.Constant;
import com.example.tiktokapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

public class UploadPost extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView thumbnail;
    private File videoFile;
    private TextInputLayout visibilityIcon;
    private String[] visibilityList = {"Ai cũng có thể nhìn thấy bài viết này", "Chỉ bạn bè của bạn mới thấy bài viết này", "Chỉ mình tôi"};
    private int visibility;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        loadVideo();
    }
    private void init() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        visibilityIcon = findViewById(R.id.visibility);
        thumbnail = findViewById(R.id.thumbnail);
        autoCompleteTextView  = findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item,visibilityList);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        visibility = Constant.VISIBILITY_POST_PUBLIC;
                        visibilityIcon.setStartIconDrawable(R.drawable.earth);
                        break;
                    case 1:
                        visibility = Constant.VISIBILITY_POST_FRIEND;
                        visibilityIcon.setStartIconDrawable(R.drawable.user_switch);

                        break;
                    case 2:
                        visibility = Constant.VISIBILITY_POST_PRIVATE;
                        visibilityIcon.setStartIconDrawable(R.drawable.lock);

                        break;
                }
            }
        });

    }
    private void loadVideo() {
        Intent intent = getIntent();
        String videoPath = intent.getStringExtra("video_path");
        videoFile = new File(videoPath);
        Glide.with(this).load(videoFile).into(thumbnail);
    }
}