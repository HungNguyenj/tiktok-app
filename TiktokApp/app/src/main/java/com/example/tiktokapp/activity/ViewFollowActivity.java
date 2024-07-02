package com.example.tiktokapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.FollowAdapter;
import com.example.tiktokapp.fragment.FollowFragment;
import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.services.ServiceGenerator;
import com.example.tiktokapp.utils.AuthUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ViewFollowActivity extends AppCompatActivity {
    private Button btnShowFragment;
    private ImageButton backBtn;

    List<Follow> followList;
    FollowAdapter adapter;
    int userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_follow);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        followList = new ArrayList<>();
        adapter = new FollowAdapter(followList);

        btnShowFragment = findViewById(R.id.btnShowFragment);
        btnShowFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(new FollowFragment());
            }
        });
        backBtn = findViewById(R.id.backBtnFollow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //set userId
        Bundle bundle = new Bundle();
        int userId = 1;
        bundle.putInt("userId",userId);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void getFollows(int userId ,Context context) {
        ServiceGenerator.createFollowService(context).getListFollowById(userId).enqueue(new retrofit2.Callback<APIResponeList<Follow>>() {
            @Override
            public void onResponse(Call<APIResponeList<Follow>> call, Response<APIResponeList<Follow>> response) {
                APIResponeList<Follow> apiResponse = response.body();
                if (response.isSuccessful()) {
                    apiResponse = response.body();
                    followList = apiResponse.getData();
                    adapter.setData(followList);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(context, apiResponse.getMes(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponeList<Follow>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}