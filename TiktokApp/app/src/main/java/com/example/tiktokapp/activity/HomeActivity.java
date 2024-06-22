package com.example.tiktokapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.Model.Post;
import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import com.example.tiktokapp.utils.IntentUtil;

public class HomeActivity extends AppCompatActivity {
    private ImageView soundDisk;
    private ImageView uploadButton;

    private List<Post> postList;
    private ViewPager2 viewPager2;
    private PostAdapter adapter;


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

        postList = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

        postList.add(new Post("title 01", "http://res.cloudinary.com/da5wewzih/video/upload/v1709014619/tiktok_video/xzzgbdzlxuo51eu9qz9q.mp4"));
        postList.add(new Post("title 02", "http://res.cloudinary.com/da5wewzih/video/upload/v1716220712/tiktok_video/qkfwangsiwkmaszsem1v.mp4"));
        postList.add(new Post("title 03", "http://res.cloudinary.com/da5wewzih/video/upload/v1716220837/tiktok_video/gqsyudrlwcbxdlp68vd2.mp4"));
        postList.add(new Post("title 04", "http://res.cloudinary.com/da5wewzih/video/upload/v1716220942/tiktok_video/qatbetefbw0bdzuvnl7o.mp4"));
        postList.add(new Post("title 05", "http://res.cloudinary.com/da5wewzih/video/upload/v1716226746/tiktok_video/ut2zoqvl9xfuwkzalw1l.mp4"));
        postList.add(new Post("title 06", "http://res.cloudinary.com/da5wewzih/video/upload/v1716358440/tiktok_video/l8r2ue2zvcd8pr8tch6b.mp4"));

        adapter = new PostAdapter(postList);
        viewPager2.setAdapter(adapter);
  
        //init();

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