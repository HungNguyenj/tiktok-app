package com.example.tiktokapp.adapter;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.R;
import com.example.tiktokapp.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Post> postList;
    private FragmentActivity context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int postId);
    }

    public PostAdapter(List<Post> postList, FragmentActivity context) {
        this.postList = postList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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
        ImageView btnCmt;
        ImageButton btnPause;
        CountDownTimer countDownTimer;
        ProgressBar progressBar;

        @SuppressLint("ClickableViewAccessibility")
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            // post element
            videoView = itemView.findViewById(R.id.videoView);
            title = itemView.findViewById(R.id.videoContent);
            likes = itemView.findViewById(R.id.amountLike);
            comments = itemView.findViewById(R.id.amountComment);
            shares = itemView.findViewById(R.id.amountShare);
            btnCmt = itemView.findViewById(R.id.btnComment);
            userName = itemView.findViewById(R.id.videoUserName);
            avatar = itemView.findViewById(R.id.userAvatar);
            progressBar = itemView.findViewById(R.id.progress_bar);
            btnPause = itemView.findViewById(R.id.btnPause);

            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePause();
                }
            });

            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    toggleControlsVisibility(true);
                    return false;
                }
            });

            btnCmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(postList.get(getAdapterPosition()).getId());
                    }
                }
            });
        }

        public void setPostData(Post post) {
            // show progress bar
            progressBar.setVisibility(View.VISIBLE);

            // set content
            title.setText(post.getTitle());
            likes.setText(String.valueOf(post.getLikes()));
            comments.setText(String.valueOf(post.getComments()));
            shares.setText(String.valueOf(post.getShares()));
            userName.setText(post.getPosterData().getUserName());

            // set avatar
            Uri avatarUri = Uri.parse(post.getPosterData().getAvatarData().getUrl().toString());
            Glide.with(itemView.getContext())
                    .load(post.getPosterData().getAvatarData().getUrl())
                    .into(avatar);

            // control video
            Uri videoUri = Uri.parse(post.getVideoUrl());
            videoView.setVideoURI(videoUri);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    btnPause.setImageResource(R.drawable.ic_media_pause);

                    // set video scale
                    int videoWidth = mp.getVideoWidth();
                    int videoHeight = mp.getVideoHeight();
                    int videoViewWidth = videoView.getWidth();
                    int videoViewHeight = videoView.getHeight();
                    if (videoWidth != 0 && videoHeight != 0 && videoViewWidth != 0 && videoViewHeight != 0) {
                        float videoRatio = (float) videoWidth / videoHeight;
                        float viewRatio = (float) videoViewWidth / videoViewHeight;

                        float scaleX = 1f;
                        float scaleY = 1f;

                        if (videoRatio >= viewRatio) {
                            scaleX = videoRatio / viewRatio;
                        } else {
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
