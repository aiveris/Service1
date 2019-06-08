package com.example.service1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int SONG_REQUESTED_CODE = 123 ;
    Button btn1,btn2,btn3,btn4;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SONG_REQUESTED_CODE && resultCode == RESULT_OK) {

            Uri mp3Uri = data.getData();
            MediaPlayer mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {
                mPlayer.setDataSource(this, mp3Uri);
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mPlayer.start();
            Log.e("RESULT", "Got Uri: " + mp3Uri.toString());
        }
    }

    //from raw resources
    public void btn1_Click(View v){
        mPlayer= MediaPlayer.create(this, R.raw.whichsong);
        //mPlayer.setLooping(true);
        //1st check if it is already playing with .... mPlayer.isPlaying();
        mPlayer.start();
    }

    //from file manager
    public void btn2_Click(View v){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("audio/*");
        startActivityForResult(i, SONG_REQUESTED_CODE);
    }

    //service
    public void btn3_Click(View v){

        Intent i = new Intent(this,MyService.class);
        i.putExtra("song",R.raw.whichsong);
        startService(i);

    }
    public void btn4_Click(View v){

        Intent i = new Intent(this,MyService.class);
        stopService(i);
    }

    public void StopActivityPlayer(View v){
        mPlayer.stop();
    }
}