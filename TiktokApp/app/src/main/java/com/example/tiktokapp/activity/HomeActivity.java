package com.example.tiktokapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiktokapp.Model.APIResponeList;
import com.example.tiktokapp.Model.Post;
import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.tiktokapp.services.PostService;
import com.example.tiktokapp.utils.IntentUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
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
        viewPager2 = findViewById(R.id.viewPager2);
        // Initialize adapter with an empty list initially
        postList = new ArrayList<>();
        postList.add(new Post("Title 01","http://res.cloudinary.com/da5wewzih/video/upload/v1709014619/tiktok_video/xzzgbdzlxuo51eu9qz9q.mp4"));
        adapter = new PostAdapter(postList);
        viewPager2.setAdapter(adapter);
        init();
        // Call API to get posts
        getPosts();
    }

    private void init() {
        // Run sound disk
        uploadButton = findViewById(R.id.btnUpload);
        uploadButton.setOnClickListener(v -> {
            IntentUtil.changeActivity(this, ChooseVideoActivity.class);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
        });
    }

    private void getPosts() {
        PostService.excute.getPosts().enqueue(new Callback<APIResponeList<Post>>() {
            @Override
            public void onResponse(Call<APIResponeList<Post>> call, Response<APIResponeList<Post>> response) {
                APIResponeList<Post> apiResponse = response.body();
                if (apiResponse.getErr() == 0) {
                    postList = apiResponse.getData();
                    adapter.setData(postList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(HomeActivity.this, apiResponse.getMes(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APIResponeList<Post>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
