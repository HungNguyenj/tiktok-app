package com.example.tiktokapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiktokapp.Constant;
import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.VideoPreviewAdapter;


public class VideoFilesFragment extends Fragment {
    private RecyclerView recyclerView;
    private VideoPreviewAdapter videoAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_files, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        videoAdapter = new VideoPreviewAdapter(getContext(), Constant.allVideoFiles);
        recyclerView.setAdapter(videoAdapter);
        return view;
    }
}