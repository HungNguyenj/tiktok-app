package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktokapp.R;
import com.example.tiktokapp.requestModel.SignUpReq;
import com.example.tiktokapp.requestModel.SignUpRes;
import com.example.tiktokapp.services.AuthService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText username, password, email, retypePassword, fullname;
    MaterialButton btnSignUp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retype_password);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        btnSignUp = findViewById(R.id.sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpReq signUpReq = new SignUpReq();
                signUpReq.setUsername(username.getText().toString());
                signUpReq.setPassword(password.getText().toString());
                signUpReq.setEmail(email.getText().toString());
                signUpReq.setFullname(fullname.getText().toString());
                Call<SignUpRes> res = AuthService.excute.register(signUpReq);
                res.enqueue(new Callback<SignUpRes>() {
                    @Override
                    public void onResponse(Call<SignUpRes> call, Response<SignUpRes> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, response.body().getMes(), Toast.LENGTH_LONG);
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpRes> call, Throwable t) {

                    }
                });
            }
        });
    }
}
