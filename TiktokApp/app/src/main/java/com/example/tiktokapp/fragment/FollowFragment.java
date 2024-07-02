package com.example.tiktokapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tiktokapp.R;
import com.example.tiktokapp.activity.HomeActivity;
import com.example.tiktokapp.adapter.FollowAdapter;
import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class FollowFragment extends Fragment {

    List<Follow> followList;
    FollowAdapter adapter;
    int userId, state;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        RecyclerView rvFollows = view.findViewById(R.id.rvFollows);

        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
        state = bundle.getInt("state");

        followList = new ArrayList<>();
        adapter = new FollowAdapter(followList);

        if (state == 0) {
            getFollows(userId, getContext());
        } else if (state == 1) {
            getFollowees(userId, getContext());
        }

        rvFollows.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFollows.setAdapter(adapter);
        return view;
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

    public void getFollowees(int userId ,Context context) {
        ServiceGenerator.createFollowService(context).getListFollowerById(userId).enqueue(new retrofit2.Callback<APIResponeList<Follow>>() {
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