package com.example.tiktokapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.R;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;
import com.example.tiktokapp.services.ServiceGenerator;
import com.example.tiktokapp.utils.HttpUtil;
import com.example.tiktokapp.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.tiktokapp.services.PostService;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {
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

        //postList.add(new Post("Title 01","http://res.cloudinary.com/da5wewzih/video/upload/v1709014619/tiktok_video/xzzgbdzlxuo51eu9qz9q.mp4"));
        adapter = new PostAdapter(postList);
        viewPager2.setAdapter(adapter);
        initNavbar(this);
        // Call API to get posts
        getPosts(this);

    }




    private void getPosts(Context context) {
        ServiceGenerator.createPostService(HomeActivity.this).getPosts().enqueue(new retrofit2.Callback<APIResponeList<Post>>() {
            @Override
            public void onResponse(Call<APIResponeList<Post>> call, Response<APIResponeList<Post>> response) {
                if (response.isSuccessful()) {
                    APIResponeList<Post> apiResponse = response.body();
                    postList = apiResponse.getData();
                    adapter.setData(postList);
                    adapter.notifyDataSetChanged();
                }else {
                    try {
                        SimpleAPIRespone errResponse = HttpUtil.parseError(response, SimpleAPIRespone.class,context);
                        Toast.makeText(context, "Error: " + errResponse.getMes(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<APIResponeList<Post>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
