package com.example.tiktokapp.adapter;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.Model.Post;
import com.example.tiktokapp.R;

import java.util.List;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

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
        TextView title, likes, comments, shares, userName;

        CircleImageView avatar;

        ImageButton btnPause;
        CountDownTimer countDownTimer;

        ProgressBar progressBar;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            //post element
            videoView = itemView.findViewById(R.id.videoView);
            title = itemView.findViewById(R.id.videoContent);
            likes = itemView.findViewById(R.id.amountLike);
            comments = itemView.findViewById(R.id.amountComment);
            shares = itemView.findViewById(R.id.amountShare);

            userName = itemView.findViewById(R.id.videoUserName);

            avatar = itemView.findViewById(R.id.userAvatar);

            //progressbar
            progressBar = itemView.findViewById(R.id.progress_bar);

            //pause button
            btnPause = itemView.findViewById(R.id.btnPause);
            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePause();
                }
            });

            //handle when touching screen
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    toggleControlsVisibility(true);
                    return false;
                }
            });

        }

        public void setPostData(Post post) {
            //show progress bar
            progressBar.setVisibility(View.VISIBLE);

            //set content
            title.setText(post.getTitle());
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
                    btnPause.setImageResource(R.drawable.ic_media_pause);


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
                    //loop video
                    mp.start();
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
    }
}
