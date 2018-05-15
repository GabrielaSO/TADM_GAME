package com.example.giso.tadm_game;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class Juego extends AppCompatActivity {
    private JuegoV juegoV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display d= getWindowManager().getDefaultDisplay();
        Point size = new Point();
        d.getSize(size);
        juegoV = new JuegoV(this,size.x,size.y);
        setContentView(juegoV);

        MediaPlayer musica = MediaPlayer.create(Juego.this,R.raw.spacee);
        musica.start();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }



    protected void onPause(){
        super.onPause();
        juegoV.pause();
    }

    protected  void onResume(){
        super.onResume();
        juegoV.resume();
    }

    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(Juego.this, R.raw.spacee);
            player.setLooping(true); // Set looping
            player.setVolume(100,100);
            player.isLooping();
            player.start();

            return null;
        }

    }
}
