package com.jeeves.firebaseuploadscoding;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;

import java.util.List;

/**
 * Created by Belal on 2/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Upload> uploads;

    public MyAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(getContentLayoutId(viewType), parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    private int getContentLayoutId(int viewType){
        switch (viewType){
            case Upload.TYPE_VIDEO:
                return R.layout.layout_videos;
            case Upload.TYPE_IMAGE:
            case Upload.TYPE_NONE:
                default:
                return R.layout.layout_images;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Upload upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());

        if(holder.imageView != null){
            Glide.with(context).load(upload.getUrl()).into(holder.imageView);
        }
        if(holder.videoview != null){
            try {
                MediaController mediaController = new MediaController(context);
                mediaController.setAnchorView(holder.videoview);
                holder.videoview.setMediaController(mediaController);
                holder.videoview.setVideoPath("https://firebasestorage.googleapis.com/v0/b/fir-uploadscoding.appspot.com/o/uploads%2F1519822256360.mp4?alt=media&token=d4bbe16f-f342-4bfd-ba6c-d1b3d59312eb");
                holder.videoview.seekTo(100);
            } catch (Exception e){

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return uploads.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;
        public VideoView videoview;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
            videoview = itemView.findViewById(R.id.videoview);
            if(videoview != null) {
                videoview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoview.start();
                    }
                });
            }
        }
    }
}
