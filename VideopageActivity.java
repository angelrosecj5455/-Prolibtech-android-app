package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.VideoView;

public class VideopageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videopage);
        VideoView videoView = findViewById(R.id.videoView);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url=sh.getString("vurl","");
        videoView.setVideoPath(url);
        videoView.start();
    }
}