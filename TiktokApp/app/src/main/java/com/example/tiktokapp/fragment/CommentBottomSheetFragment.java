package com.example.tiktokapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tiktokapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CommentBottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comment_bottom_sheet,
                container, false);


//        algo_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),
//                        "Algorithm Shared", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });
//
//        course_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),
//                        "Course Shared", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });
        return v;
    }
}
