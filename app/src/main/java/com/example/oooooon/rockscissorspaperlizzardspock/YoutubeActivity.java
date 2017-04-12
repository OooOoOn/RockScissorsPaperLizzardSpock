package com.example.oooooon.rockscissorspaperlizzardspock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity {

    Button btnPlay;
    YouTubePlayerView youTubePlayerView;//player_view
    YouTubePlayer.OnInitializedListener onInitializedListener; // pause button fullscreen etc..



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        //ref
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("iapcKVn7DdY");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btnPlay = (Button) findViewById(R.id.button);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize("AIzaSyDmlXcIauWabb3PqGxvJ21jghhDfO76EX8",onInitializedListener);
            }
        });

    }
}
