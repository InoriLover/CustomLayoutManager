package com.customlayoutmanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.customlayoutmanager.widget.media.AndroidMediaController;
import com.customlayoutmanager.widget.media.IjkVideoView;
import com.customlayoutmanager.widget.media.InfoHudViewHolder;

import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.exceptions.IjkMediaException;


/**
 * Created by Fishy on 2017/5/27.
 */

public class VideoActivity extends AppCompatActivity {
    IjkVideoView videoView;
    IjkMediaPlayer
    private TableLayout mHudView;
    private AndroidMediaController mMediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(getSupportActionBar());
        mHudView = (TableLayout) findViewById(R.id.hud_view);
        videoView= (IjkVideoView) findViewById(R.id.video_view);
        videoView.setMediaController(mMediaController);
        videoView.setHudView(mHudView);
//        videoView.setVideoURI(Uri.parse("http://stream2.ahtv.cn/jjsh/cd/live.m3u8"));
        videoView.setVideoURI(Uri.parse("rtsp://c.itvitv.com/hj.kprkjmf"));
        videoView.start();
    }
}
