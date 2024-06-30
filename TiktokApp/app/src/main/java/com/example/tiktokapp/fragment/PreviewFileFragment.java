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
import com.example.tiktokapp.adapter.FilePreviewAdapter;

import java.util.ArrayList;
import java.util.List;


public class PreviewFileFragment extends Fragment {
    private RecyclerView recyclerView;
    private FilePreviewAdapter videoAdapter;
    private int requestCode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            requestCode = getArguments().getInt("requestCode", -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_files, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        List<Object> files = new ArrayList<>();
        if (requestCode == Constant.REQUEST_CODE_GET_VIDEO_LIST) {
            files.addAll(Constant.allVideoFiles);
        } else if (requestCode == Constant.REQUEST_CODE_GET_IMAGE_LIST) {
            files.addAll(Constant.allImageFiles);
        }
        videoAdapter = new FilePreviewAdapter(getContext(), files,requestCode);
        recyclerView.setAdapter(videoAdapter);
        return view;

    }
}