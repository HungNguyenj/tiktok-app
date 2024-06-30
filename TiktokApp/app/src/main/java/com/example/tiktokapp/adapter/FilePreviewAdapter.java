package com.example.tiktokapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiktokapp.Constant;
import com.example.tiktokapp.R;
import com.example.tiktokapp.activity.UploadPost;
import com.example.tiktokapp.utils.IntentUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FilePreviewAdapter extends RecyclerView.Adapter<FilePreviewAdapter.FileViewHolder> {
    private final List<Object> models;
    private final Context context;
    private int type;
    public FilePreviewAdapter(Context context, List<Object> models, int type) {
        this.context = context;
        this.models = models;
        this.type = type;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        Object item = models.get(position);
        switch (type) {
            case Constant.REQUEST_CODE_GET_VIDEO_LIST :
                File videoFile = (File) item;
                if (Constant.allVideoFiles.indexOf(videoFile)!=-1) {
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(videoFile.getAbsolutePath());
                    String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                    long timeInMillisec = Long.parseLong(time);
                    String duration = String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(timeInMillisec),
                            TimeUnit.MILLISECONDS.toSeconds(timeInMillisec) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillisec))
                    );
                    holder.videoDuration.setText(duration);
                    try {
                        retriever.release();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    holder.videoDuration.setText("");
                }
                Glide.with(context).load(videoFile).into(holder.videoThumbnail);
                holder.itemView.setOnClickListener(v -> {
                    Constant.videoPathUpload = videoFile.getAbsolutePath();
                    IntentUtil.changeActivityAndPutString(holder.itemView.getContext(), UploadPost.class,"video_path",videoFile.getAbsolutePath());
                });
                break;
            case Constant.REQUEST_CODE_GET_IMAGE_LIST :
                File imageFile = (File) item;
                holder.videoDuration.setText("");
                Glide.with(context).load(imageFile).into(holder.videoThumbnail);
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, UploadPost.class);
                    intent.putExtra("video_path", Constant.videoPathUpload);
                    intent.putExtra("image_path", imageFile.getAbsolutePath());
                    context.startActivity(intent);
                    if (context instanceof Activity) {
                        ((Activity) context).finish(); // Finish the current activity
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class FileViewHolder extends RecyclerView.ViewHolder {
        ImageView videoThumbnail;
        TextView videoDuration;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoDuration = itemView.findViewById(R.id.videoDuration);
        }
    }
}
