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
import com.example.tiktokapp.responseModel.User;
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
                signUpReq.setUserName(username.getText().toString());
                signUpReq.setPassword(password.getText().toString());
                signUpReq.setEmail(email.getText().toString());
                signUpReq.setFullName(fullname.getText().toString());
                signUpReq.setAssociation("");
                Call<User> res = AuthService.excute.register(signUpReq);
                res.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Registered successfully, Your otp has been sent to email address. OTP will be expired in 5 minutes", Toast.LENGTH_LONG);
                            startActivity(new Intent(SignUpActivity.this, VerifyEmailActivity.class));
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
