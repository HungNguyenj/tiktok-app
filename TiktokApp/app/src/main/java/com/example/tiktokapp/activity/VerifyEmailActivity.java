package com.example.tiktokapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktokapp.R;
import com.example.tiktokapp.requestModel.VerifyEmailReq;
import com.example.tiktokapp.requestModel.VerifyEmailRes;
import com.example.tiktokapp.services.AuthService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyEmailActivity extends AppCompatActivity {
   EditText otp, email;
   MaterialButton next;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.fragment_verify_email);
      otp = findViewById(R.id.otp);
      email = findViewById(R.id.email);
      next = findViewById(R.id.next);
      next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            VerifyEmailReq verifyEmailReq = new VerifyEmailReq();
            verifyEmailReq.setOtp(otp.getText().toString());
            verifyEmailReq.setEmail(email.getText().toString());
            Call<VerifyEmailRes> res = AuthService.excute.vertifyEmail(verifyEmailReq);
            res.enqueue(new Callback<VerifyEmailRes>() {
               @Override
               public void onResponse(Call<VerifyEmailRes> call, Response<VerifyEmailRes> response) {
                  if(response.isSuccessful()){
                     Toast.makeText(VerifyEmailActivity.this, response.body().getMes(), Toast.LENGTH_LONG);
                     startActivity(new Intent(VerifyEmailActivity.this, LoginActivity.class));
                  }
               }

               @Override
               public void onFailure(Call<VerifyEmailRes> call, Throwable t) {

               }
            });
         }
      });
   }
}
