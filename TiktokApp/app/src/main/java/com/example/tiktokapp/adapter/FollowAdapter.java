package com.example.tiktokapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.activity.ProfileOtherUserActivity;
import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;
import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.services.ServiceGenerator;
import com.example.tiktokapp.utils.HttpUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        LinearLayout linearLayout;

        public FollowViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.fi_FollowImage);
            fullName = itemView.findViewById(R.id.fi_FullName);
            userName = itemView.findViewById(R.id.fi_UserName);
            btnFollow = itemView.findViewById(R.id.fi_BtnFollow);
            linearLayout = itemView.findViewById(R.id.fi_layout);
        }

        public void setFollowData(Follow follow) {
            Uri avatarUri = Uri.parse(follow.getFollowerData().getAvatarData().getUrl());
            Glide.with(itemView.getContext())
                    .load(avatarUri)
                    .into(avatar);

            fullName.setText(follow.getFollowerData().getFullName() + "");
            userName.setText(follow.getFollowerData().getUserName() + "");

            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //bấm vào để mở thông tin cá nhân của người được chọn
                    Intent intent = new Intent(itemView.getContext(), ProfileOtherUserActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //bấm vào để mở thông tin cá nhân của người được chọn
                    Intent intent = new Intent(itemView.getContext(), ProfileOtherUserActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

            if (follow.getIsFriend() == 1) {
                btnFollow.setBackgroundColor(Color.rgb(68, 230, 9));
                btnFollow.setText("Friend");
            } else if (follow.getIsFollow() == 1) {
                btnFollow.setBackgroundColor(Color.rgb(237, 166, 190));
                btnFollow.setText("Following");
            }

            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (follow.getIsFriend() == 1) {
                        //xử lý khi nhấn vào nút bạn
                    } else if (follow.getIsFollow() == 1) {
                        //xử lý khi nhấn vào nút follow (-> chuyển thành unfollow)

                        btnFollow.setBackgroundColor(Color.rgb(233, 30, 99));
                        btnFollow.setText("Follow");
                    } else if (follow.getIsFollowee() == 1) {
                        //xử lý khi nhấn vào nút follow (-> chuyển thành unfollow)

                        btnFollow.setBackgroundColor(Color.rgb(237, 166, 190));
                        btnFollow.setText("Following");
                    }
                }
            });

        }

        private void follow(User user, Context context) {
            ServiceGenerator.createFollowService(context).follow(user.getId()).enqueue(new Callback<APIRespone<Follow>>() {
                @Override
                public void onResponse(Call<APIRespone<Follow>> call, Response<APIRespone<Follow>> response) {
                    if(response.isSuccessful()&& response!=null){
                        APIRespone<Follow> apiResponse = response.body();
                        if (apiResponse.getErr() == 0) {
                            // Xử lý khi like thành công
                            Log.i("follow", "Folow thành công");
                            // Cập nhật icon của cho userFollow
                            btnFollow.setBackgroundColor(Color.rgb(237, 166, 190));
                            btnFollow.setText("Following");
                        }
                    }
                    else{
                        try {
                            SimpleAPIRespone errResponse = HttpUtil.parseError(response, SimpleAPIRespone.class,context);
                            Toast.makeText(context, "Error: " + errResponse.getMes(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<APIRespone<Follow>> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Toast.makeText(context, "Lỗi khi follow", Toast.LENGTH_SHORT).show();
                    Log.i("follow", "Call api thất bại");
                }
            });
        }

        private void unFollow(User user, Context context) {
            ServiceGenerator.createFollowService(context).unFollow(user.getId()).enqueue(new Callback<SimpleAPIRespone>() {
                @Override
                public void onResponse(Call<SimpleAPIRespone> call, Response<SimpleAPIRespone> response) {
                    if (response.isSuccessful()) {
                        SimpleAPIRespone apiResponse = response.body();
                        // Xử lý khi unlike thành công
                        // Cập nhật icon của cho userFollow
                        btnFollow.setBackgroundColor(Color.rgb(233, 30, 99));
                        btnFollow.setText("Follow");
                    } else {
                        try {
                            SimpleAPIRespone errResponse = HttpUtil.parseError(response, SimpleAPIRespone.class,context);
                            Toast.makeText(context, "Error: " + errResponse.getMes(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<SimpleAPIRespone> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Log.i("UnFollow", "Call api thất bại");
                }
            });
        }
    }
}
