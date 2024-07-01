package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktokapp.R;
import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.PostService;
import com.example.tiktokapp.utils.IntentUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText searchInput;
    TextView searchButtonEnter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchInput.findViewById(R.id.search_input);
        searchButtonEnter.findViewById(R.id.search_icon_enter);

        searchButtonEnter.setOnClickListener(
                v -> {
                    String seacrhTitle = searchInput.getText().toString().trim();

//
                    Intent intent = new Intent(this, ListSearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", seacrhTitle);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
        );
    }
}
