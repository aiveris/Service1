package com.example.service1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SERVICE-X", "onStartCommand..............." + intent);

        int resourceValue = intent.getIntExtra("song",0);

        mediaPlayer = MediaPlayer.create(this, resourceValue);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        return START_STICKY;// START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}