package com.example.tiktokapp.adapter;

import static com.example.tiktokapp.utils.SharePreferncesUtil.clear;
import static com.example.tiktokapp.utils.SharePreferncesUtil.isLike;
import static com.example.tiktokapp.utils.SharePreferncesUtil.like;
import static com.example.tiktokapp.utils.SharePreferncesUtil.unlike;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.model.FollowAPIRespone;
import com.example.tiktokapp.model.Post;
import com.example.tiktokapp.model.SimpleAPIRespone;
import com.example.tiktokapp.R;
import com.example.tiktokapp.model.User;
import com.example.tiktokapp.services.PostService;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main, parent, false);
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
        TextView title, likes, comments, shares, userName;

        CircleImageView avatar;
        ProgressBar progressBar;


        // Khai báo ImageView heartButton
        ImageView heartButton;

        // Khai báo TextView amountLike
        TextView amountLike;
        ImageButton btnPause;
        CountDownTimer countDownTimer;
        ImageView userFollow;


        public PostHolder(@NonNull View itemView) {
            super(itemView);

            //post element
            //progressbar
            progressBar = itemView.findViewById(R.id.progress_bar);

            //pause button
            btnPause = itemView.findViewById(R.id.btnPause);
            // Khởi tạo ImageView heartButton
            heartButton = itemView.findViewById(R.id.btnLike);
            // Khởi tạo TextView amountLike
            amountLike = itemView.findViewById(R.id.amountLike);
            // Khởi tạo ImageView userFollow
            userFollow = itemView.findViewById(R.id.userAvatar);
            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePause();
                }
            });
            videoView = itemView.findViewById(R.id.videoView);

            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    toggleControlsVisibility(true);
                    return false;
                }
            });
            title = itemView.findViewById(R.id.videoContent);
            likes = itemView.findViewById(R.id.amountLike);
            comments = itemView.findViewById(R.id.amountComment);
            shares = itemView.findViewById(R.id.amountShare);

            userName = itemView.findViewById(R.id.videoUserName);

            avatar = itemView.findViewById(R.id.userAvatar);

            //progressbar
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
            likes.setText(String.valueOf(post.getLikes()));
            comments.setText(String.valueOf(post.getComments()));
            shares.setText(String.valueOf(post.getShares()));
            userName.setText(post.getPosterData().getUserName());
            //set avatar
            Uri avatarUri = Uri.parse(post.getPosterData().getAvatarData().getUrl().toString());


            Glide.with(itemView.getContext())
                    .load(post.getPosterData().getAvatarData().getUrl())
                    .into(avatar);

            //control video
            MediaController mediaController = new MediaController(itemView.getContext());
            mediaController.setAnchorView(videoView);
//            videoView.setMediaController(mediaController);

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


            //render heartbtn phụ thuộc vào đã like hay  chưa
            clear(itemView.getContext());
            if(isLike(itemView.getContext(), String.valueOf(post.getId()))){
                heartButton.setColorFilter(0xfffc1605);
            }

            // Xử lý sự kiện click cho ImageView heartButton
            heartButton.setOnClickListener((view) -> {
                if (isLike(itemView.getContext(), String.valueOf(post.getId()))) {
                    unlikePost(post, itemView.getContext());
                } else {
                    likePost(post, itemView.getContext());
                }
            });


            // Kiểm tra xem đã follow hay chưa.
            boolean isFollowed = false;
            // Set dữ liệu cho userFollow
            userFollow.setImageResource(isFollowed ? R.drawable.tick : R.drawable.plus);
            // Xử lý sự kiện click cho ImageView userFollow
            userFollow.setOnClickListener((v) -> {
                if (isFollowed) {
                    unFollow(post.getPosterData(), itemView.getContext());
                } else {
                    follow(post.getPosterData(), itemView.getContext());
                }
            });
        }


        // Hàm gọi api và xử lý sự kiện like
        private void likePost(Post post, Context context) {
            PostService.excute.likePost(post.getId()).enqueue(new Callback<SimpleAPIRespone>() {
                @Override
                public void onResponse(Call<SimpleAPIRespone> call, Response<SimpleAPIRespone> response) {
                    if(response.body() !=null){
                        SimpleAPIRespone apiResponse = response.body();
                        if ( apiResponse.getErr() == 0 ) {
                            // Xử lý khi like thành công
                            Log.i("likePost", "Like thành công");
                            // Cập nhật màu của ImageView heartButton


                            heartButton.setColorFilter(0xfffc1605);
                            // Cập nhật số lượng like
                            post.setLikes(post.getLikes() + 1);
                            amountLike.setText(String.valueOf(post.getLikes()));
                            like(context,String.valueOf(post.getId()));
                        } else {
                            // Xử lý khi like video lỗi
                            Toast.makeText(context, "Lỗi khi like post", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        System.out.println(response.toString());
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

        private void togglePause() {
            if (videoView.isPlaying()) {
                videoView.pause();
                btnPause.setImageResource(R.drawable.ic_media_play);
            } else {
                videoView.start();
                btnPause.setImageResource(R.drawable.ic_media_pause);
            }
        }

        private void toggleControlsVisibility(boolean resetTimer) {
            if (btnPause.getVisibility() == View.VISIBLE) {
                btnPause.setVisibility(View.INVISIBLE);
            } else {
                btnPause.setVisibility(View.VISIBLE);
            }

            if (resetTimer) {
                startTimer();
            }
        }

        private void startTimer() {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            countDownTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    toggleControlsVisibility(false);
                }
            }.start();
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
                        heartButton.clearColorFilter();
                        // Cập nhật số lượng like
                        post.setLikes(post.getLikes() - 1);
                        amountLike.setText(String.valueOf(post.getLikes()));
                        unlike(context,String.valueOf(post.getId()));
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

        // Hàm gọi api và xử lý sự kiện follow
        private void follow(User user, Context context) {
            PostService.excute.follow(user.getId()).enqueue(new Callback<SimpleAPIRespone>() {
                @Override
                public void onResponse(Call<SimpleAPIRespone> call, Response<SimpleAPIRespone> response) {
                   if(response.isSuccessful()&& response!=null){
                       SimpleAPIRespone apiResponse = response.body();
                       if (apiResponse.getErr() == 0) {
                           // Xử lý khi like thành công
                           Log.i("follow", "Folow thành công");
                           // Cập nhật icon của cho userFollow
                           userFollow.setImageResource(R.drawable.tick);
                       } else {
                           // Xử lý khi like video lỗi
                           Toast.makeText(context, "Lỗi khi follow", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       System.out.println("*******************************"+response);
                   }
                }

                @Override
                public void onFailure(Call<SimpleAPIRespone> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Toast.makeText(context, "Lỗi khi follow", Toast.LENGTH_SHORT).show();
                    Log.i("follow", "Call api thất bại");
                }
            });
        }

        // Hàm gọi api và xử lý sự kiện unfollow
        private void unFollow(User user, Context context) {
            PostService.excute.unFollow(user.getId()).enqueue(new Callback<FollowAPIRespone>() {
                @Override
                public void onResponse(Call<FollowAPIRespone> call, Response<FollowAPIRespone> response) {
                    SimpleAPIRespone apiResponse = response.body();
                    if (apiResponse.getErr() == 0) {
                        // Xử lý khi unlike thành công
                        // Cập nhật icon của cho userFollow
                        userFollow.setImageResource(R.drawable.plus);
                    } else {
                        Log.i("unFollow", "UnFollow thất bại");
                        // Xử lý khi unlike video lỗi
                        Toast.makeText(context, "Lỗi khi UnFollow", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                  //  Toast.makeText(context,"Lỗi khi UnFollow",Toast.LENGTH_SHORT).show();

                public void onFailure(Call<FollowAPIRespone> call, Throwable t) {
                    // Xử lý khi call api thất bại
                    Log.i("UnFollow", "Call api thất bại");
                }
            });
        }
    }
}

