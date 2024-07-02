package com.example.tiktokapp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.responseModel.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FollowViewHolder> {
    private List<Follow> followList;

    public FollowAdapter(List<Follow> followList) {
        this.followList = followList;
    }

    @NonNull
    @Override
    public FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follow_item, parent, false);
        return new FollowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowViewHolder holder, int position) {
        holder.setFollowData(followList.get(position));
    }

    public void setData(List<Follow> followList) {
        this.followList = followList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return followList.size();
    }


    public class FollowViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView fullName, userName;
        Button btnFollow;


        public FollowViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.fi_FollowImage);
            fullName = itemView.findViewById(R.id.fi_FullName);
            userName = itemView.findViewById(R.id.fi_UserName);
            btnFollow = itemView.findViewById(R.id.fi_BtnFollow);
        }

        public void setFollowData(Follow follow) {
            Uri avatarUri = Uri.parse(follow.getFollowerData().getAvatarData().getUrl());
            Glide.with(itemView.getContext())
                    .load(avatarUri)
                    .into(avatar);
            fullName.setText(follow.getFollowerData().getFullName() + "");
            userName.setText(follow.getFollowerData().getUserName() + "");

        }
    }
}
