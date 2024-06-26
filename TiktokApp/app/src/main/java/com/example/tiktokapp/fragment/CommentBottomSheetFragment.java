package com.example.tiktokapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktokapp.R;
import com.example.tiktokapp.adapter.CommentAdapter;
import com.example.tiktokapp.model.APIResponeList;
import com.example.tiktokapp.model.Comment;
import com.example.tiktokapp.services.CommentService;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentBottomSheetFragment extends BottomSheetDialogFragment {

    private int postId;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;

    public CommentBottomSheetFragment(int postId) {
        this.postId = postId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_bottom_sheet, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(commentAdapter);

        getComments();

        return view;
    }

    private void getComments() {
        CommentService.excute.getComments(postId).enqueue(new Callback<APIResponeList<Comment>>() {
            @Override
            public void onResponse(Call<APIResponeList<Comment>> call, Response<APIResponeList<Comment>> response) {
                APIResponeList<Comment> apiResponse = response.body();
                if (apiResponse.getErr() == 0) {
                    commentList = apiResponse.getData();
                    commentAdapter.setData(commentList); // Update data in adapter
                } else {
                    Toast.makeText(getContext(), apiResponse.getMes(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponeList<Comment>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
