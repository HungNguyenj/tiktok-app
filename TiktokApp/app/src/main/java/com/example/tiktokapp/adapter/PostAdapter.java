package com.example.tiktokapp.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.Model.APIResponeList;
import com.example.tiktokapp.Model.Post;
import com.example.tiktokapp.Model.SimpleAPIRespone;
import com.example.tiktokapp.R;
import com.example.tiktokapp.activity.HomeActivity;
import com.example.tiktokapp.services.PostService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main , parent, false);
        ImageView soundDisk = view.findViewById(R.id.soundDisk);
        Glide.with(view.getContext()).load(R.drawable.disk).into(soundDisk);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.setPostData(postList.get(position));
    }
    public void setData(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        TextView title;
        ProgressBar progressBar;

        // Khai báo ImageView heartButton
        ImageView heartButton;

        // Khai báo TextView amountLike
        TextView amountLike;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            title = itemView.findViewById(R.id.videoContent);
            progressBar = itemView.findViewById(R.id.progress_bar);
            // Khởi tạo ImageView heartButton
            heartButton = itemView.findViewById(R.id.btnLike);
            // Khởi tạo TextView amountLike
            amountLike = itemView.findViewById(R.id.amountLike);
        }

        public void setPostData(Post post) {
            //show progress bar
            progressBar.setVisibility(View.VISIBLE);

            //set content
            title.setText(post.getTitle());
//            videoView.setVideoPath(post.getVideoUrl());

            MediaController mediaController = new MediaController(itemView.getContext());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            Uri videoUri = Uri.parse(post.getVideoUrl());
            videoView.setVideoURI(videoUri);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);

                    //kich thuoc cua video
                    int videoWidth = mp.getVideoWidth();
                    int videoHeight = mp.getVideoHeight();

                    // Lấy kích thước của VideoView
                    int videoViewWidth = videoView.getWidth();
                    int videoViewHeight = videoView.getHeight();

                    // Kiểm tra xem có thể tính toán tỷ lệ không
                    if (videoWidth != 0 && videoHeight != 0 && videoViewWidth != 0 && videoViewHeight != 0) {
                        float videoRatio = (float) videoWidth / videoHeight;
                        float viewRatio = (float) videoViewWidth / videoViewHeight;

                        float scaleX = 1f;
                        float scaleY = 1f;

                        if (videoRatio >= viewRatio) {
                            // Video có tỷ lệ rộng hơn hoặc bằng View
                            scaleX = videoRatio / viewRatio;
                        } else {
                            // Video có tỷ lệ cao hơn View
                            scaleY = viewRatio / videoRatio;
                        }
                        videoView.setScaleX(scaleX);
                        videoView.setScaleY(scaleY);
                    }

                    mp.start();
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mp.start();
                }
            });

            // Set dữ liệu cho TextView amountLike
            amountLike.setText(String.valueOf(post.getLikes()));

            // Xử lý sự kiện click cho ImageView heartButton
            heartButton.setOnClickListener((view) -> {
                if (post.isLiked()) {
                    unlikePost(post, itemView.getContext());
                } else {
                    likePost(post, itemView.getContext());
                }
            });
        }

        // Hàm gọi api và xử lý sự kiện like
        private void likePost(Post post, Context context) {
            PostService.excute.likePost(post.getId()).enqueue(new Callback<SimpleAPIRespone>() {
                @Override
                public void onResponse(Call<SimpleAPIRespone> call, Response<SimpleAPIRespone> response) {
                    SimpleAPIRespone apiResponse = response.body();
                    if (apiResponse.getErr() == 0) {
                        // Xử lý khi like thành công
                        Log.i("likePost", "Like thành công");
                        // Cập nhật màu của ImageView heartButton
                        heartButton.setColorFilter(0xfffc1605);
                        // Cập nhật số lượng like
                        post.setLikes(post.getLikes() + 1);
                        amountLike.setText(String.valueOf(post.getLikes()));
                    } else {
                        // Xử lý khi like video lỗi
                        Toast.makeText(context, "Lỗi khi like post", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<SimpleAPIRespone> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Toast.makeText(context, "Lỗi khi like post", Toast.LENGTH_SHORT).show();
                    Log.i("likePost", "Call api thất bại");
                }
            });
        }

        // Hàm gọi api và xử lý sự kiện unlike
        private void unlikePost(Post post, Context context) {
            PostService.excute.unlikePost(post.getId()).enqueue(new Callback<SimpleAPIRespone>() {
                @Override
                public void onResponse(Call<SimpleAPIRespone> call, Response<SimpleAPIRespone> response) {
                    SimpleAPIRespone apiResponse = response.body();
                    if (apiResponse.getErr() == 0) {
                        // Xử lý khi unlike thành công
                        // Cập nhật màu của ImageView heartButton
                        heartButton.setColorFilter(0xfffc1605);
                        // Cập nhật số lượng like
                        post.setLikes(post.getLikes() - 1);
                        amountLike.setText(String.valueOf(post.getLikes()));
                    } else {
                        Log.i("unlikePost", "Like thất bại");
                        // Xử lý khi unlike video lỗi
                        Toast.makeText(context, "Lỗi khi unlike post", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<SimpleAPIRespone> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Log.i("unlikePost", "Call api thất bại");
                    Toast.makeText(context, "Lỗi khi unlike post", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
