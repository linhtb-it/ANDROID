package com.example.watchnow_project.View;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watchnow_project.R;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView vv_Video;
    ImageView img_Avatar_PlayVideo;
    TextView tv_Title_PlayVideo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        vv_Video = findViewById(R.id.vv_Video);
        tv_Title_PlayVideo = findViewById(R.id.tv_Title_Video_Play);
        img_Avatar_PlayVideo = findViewById(R.id.img_Avatar_PlayVideo);
    }
}
