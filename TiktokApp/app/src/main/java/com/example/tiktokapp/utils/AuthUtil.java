package com.example.tiktokapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.UserService;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthUtil {
    private static User currentUser;
    public static User getCurrentUser() {
        CountDownLatch latch = new CountDownLatch(1);
        UserService.execute.me().enqueue(new Callback<APIRespone<User>>() {
            @Override
            public void onResponse(Call<APIRespone<User>> call, Response<APIRespone<User>> response) {
                if (response.isSuccessful()) {
                    currentUser = response.body().getData();
                }
                latch.countDown();
            }

            @Override
            public void onFailure(Call<APIRespone<User>> call, Throwable t) {
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return currentUser;
    }
    public static boolean loggedIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("accessToken", null);
        return accessToken!= null && accessToken!="";
    }
    public static String getAccessToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        return preferences.getString("accessToken", null);
    }
}
