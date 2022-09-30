package com.classicartsshowcase.firetv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private final int DELAY = 10000;
    private boolean backPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // turn off the banner
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // start activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Wait 10 sec");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(backPressed == false) {
                    Log.d(TAG, "Start Player now");
                    Intent myIntent = new Intent(MainActivity.this, PlayOnDeviceActivity.class);
                    startActivity(myIntent);
                }else{
                    Log.d(TAG, "Back Pressed, NOT Starting Player now");
                }
            }
        }, 3000);
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
        //  Here we trap back pressed. we exit if pressed
        super.onBackPressed();
        Log.d(TAG, "In onBackPressed(), Exiting");
        backPressed = true;
        finishAffinity();
    }

    @Override
    protected void onUserLeaveHint() {
        //  Here we trap home pressed. we exit if pressed
        super.onUserLeaveHint();
        Log.d(TAG, "In onUserLeaveHint(), Exiting");
        backPressed = true;
        finishAffinity();
    }

}
