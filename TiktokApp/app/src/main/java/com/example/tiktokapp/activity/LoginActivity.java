package com.example.tiktokapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktokapp.Model.User;
import com.example.tiktokapp.R;
import com.example.tiktokapp.req_res.LoginReq;

import com.example.tiktokapp.services.APIClient;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText password, email;
    MaterialButton btnLogin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();
        if(preferences.contains("accessToken")){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        btnLogin = findViewById(R.id.log_in_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginReq loginReq = new LoginReq();
                loginReq.setPassword(password.getText().toString());
                loginReq.setEmail(email.getText().toString());
                Call<User> res = APIClient.getUserService().userLogin(loginReq);
                res.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            editor.putString("accessToken", response.body().getAccessToken());
                            editor.putString("username", response.body().getUserName());
                            editor.putString("fullName", response.body().getFullName());
                            editor.putString("email", response.body().getEmail());
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }
}
