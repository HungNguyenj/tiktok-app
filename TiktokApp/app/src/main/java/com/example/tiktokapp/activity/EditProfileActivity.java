package com.example.tiktokapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.ServiceGenerator;
import com.example.tiktokapp.utils.HttpUtil;
import com.example.tiktokapp.utils.IntentUtil;
import com.example.tiktokapp.utils.SharePreferncesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView avatar, btnBack, copyLink;
    private TextView txtUsername, tiktokID;

    private String username,  fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        avatar = findViewById(R.id.userAvatar);
        txtUsername = findViewById(R.id.nameOfUser);
        tiktokID = findViewById(R.id.tiktokID);

        SharedPreferences preferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        username = preferences.getString("username", "");
        fullname = preferences.getString("fullName", "");

        txtUsername.setText(fullname);
        tiktokID.setText(username);


        txtUsername.setOnClickListener(v -> {
            IntentUtil.changeActivity(this, EditInfoActivity.class);
        });
    }

}
