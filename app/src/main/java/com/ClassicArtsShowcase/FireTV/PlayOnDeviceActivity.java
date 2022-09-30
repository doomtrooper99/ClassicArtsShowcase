package com.classicartsshowcase.firetv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayOnDeviceActivity extends AppCompatActivity {

    private final String TAG = "PlayOnDeviceActivity";
    private VideoView videoView;

    private String VIDEOPATH = "https://classicarts.global.ssl.fastly.net/live/cas/master.m3u8";
    private static ProgressDialog progressDialog;
    String videourl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // turn off the banner
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // start activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_on_device);

        Log.d(TAG, "In PlayOnDeviceActivity");

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        //
        videoView = (VideoView)findViewById(R.id.videoView);

        // playing video in VideoView from https://stackoverflow.com/questions/9765989/streaming-video-with-videoview
        progressDialog = ProgressDialog.show(PlayOnDeviceActivity.this, "", "Buffering video...", true);
        progressDialog.setCancelable(true);
        PlayVideo();

    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.d(TAG, "In onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Log.d(TAG, "In onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(isFinishing() == true){
            Log.d(TAG, "In isFinishing()");
        }

        Log.d(TAG, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();

        Log.d(TAG, "In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.d(TAG, "In onDestroy()");
    }

    @Override
    public void onBackPressed() {
        //  Here we trap home pressed. we exit if pressed
        super.onBackPressed();
        Log.d(TAG, "In onBackPressed(), Exiting");
        finishAffinity();
    }

    @Override
    protected void onUserLeaveHint() {
        //  Here we trap home pressed. we exit if pressed
        super.onUserLeaveHint();
        Log.d(TAG, "In onUserLeaveHint(), Exiting");
        finishAffinity();
    }

    private void PlayVideo()
    {
        try
        {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(PlayOnDeviceActivity.this);
            mediaController.setAnchorView(videoView);

            Uri video = Uri.parse(VIDEOPATH);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {
                public void onPrepared(MediaPlayer mp)
                {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });


        }
        catch(Exception e)
        {
            progressDialog.dismiss();
            Log.d("TAG", "Video Play Error :"+e.toString());
            finish();
        }

    }

}
