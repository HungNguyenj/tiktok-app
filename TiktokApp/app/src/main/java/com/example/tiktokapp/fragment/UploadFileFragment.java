package com.example.tiktokapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiktokapp.R;


public class UploadFileFragment extends Fragment {
    private int tab = 0;


    public UploadFileFragment(int tab) {
        this.tab = tab;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_file, container, false);
        TextView tv = view.findViewById(R.id.test);
        tv.setText("Tab " + tab);
        return view;
    }
}