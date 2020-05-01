package com.example.video;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setTheme(R.style.AppTheme2);
        setContentView(R.layout.video);
        String path=getIntent().getStringExtra("VIDEO_PATH");
        Uri uri=Uri.parse(path);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //DisplayMetrics metrics=new DisplayMetrics();
        VideoView videoView=(VideoView) findViewById(R.id.videoview);
        //videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);
        videoView.setVideoURI(uri);
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //videoView.setLayoutParams(new RelativeLayout.LayoutParams(metrics.widthPixels,metrics.heightPixels));
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
