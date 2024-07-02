package com.example.tiktokapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiktokapp.Constant;
import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.FilePreviewAdapter;
import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.services.PostService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListVideoFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_video, container, false);
        if(getArguments() != null){
            String title = getArguments().getString("title");
            Call<APIResponeList<Post>> res = PostService.excute.getPostsByTitle(title);
                    res.enqueue(new Callback<APIResponeList<Post>>() {
                        @Override
                        public void onResponse(Call<APIResponeList<Post>> call, Response<APIResponeList<Post>> response) {
                            if(response.isSuccessful()){
                                APIResponeList<Post> apiResponeList = response.body();
                                List<Post> posts = apiResponeList.getData();
                                List<File> postSearch = new ArrayList<>();
//                                for (Post p: posts) {
//                                    postSearch.add(p.getVideoUrl());
//                                }
//                                ViewPager2 listViewVideo = findViewById(R.id.layout_list_video);
//                                listViewVideo.setAdapter(new FilePreviewAdapter(getContext(), postSearch));
                            }
                        }

                        @Override
                        public void onFailure(Call<APIResponeList<Post>> call, Throwable t) {

                        }


                    });
        };
        return view;
    }
}
