package com.example.tiktokapp.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.responseModel.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bind(commentList.get(position), commentList.size());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void setData(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView commentUserName, commentContent, amountComment;
        private ImageView avatarCommenter;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarCommenter = itemView.findViewById(R.id.commenterAvatar);
            commentUserName = itemView.findViewById(R.id.commenterUsername);
            commentContent = itemView.findViewById(R.id.commentContent);
            amountComment = itemView.findViewById(R.id.amountComment);
        }

        public void bind(Comment comment, int length) {
            // set avatar
            String link = comment.getCommenterData().getAvatarData().getUrl().toString();
            Uri avatarUri = Uri.parse(link);
            Glide.with(itemView.getContext())
                    .load(avatarUri)
                    .into(avatarCommenter);
            commentUserName.setText(comment.getCommenterData().getUserName());
            commentContent.setText(comment.getContent());
        }


    }
}
