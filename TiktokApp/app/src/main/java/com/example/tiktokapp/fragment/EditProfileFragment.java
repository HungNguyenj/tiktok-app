package com.example.tiktokapp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiktokapp.R;


public class EditProfileFragment extends Fragment {

    private ImageView avatar, btnBack, copyLink;
    private TextView username, tiktokID;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_edit_info, container, false);
        avatar = view.findViewById(R.id.userAvatar);
        username = view.findViewById(R.id.username);
        tiktokID = view.findViewById(R.id.tiktokID);



        return view;
    }
}