package com.example.naveenk.amedia;

/**
 * Created by naveenk on 11/5/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class Splash extends Activity {
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this,R.raw.hoppa);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox", false);
        if(music == true){
            ourSong.start();
        }
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1600);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent openStartingPoint = new Intent("com.example.naveenk.amedia.MENU");
                    startActivity(openStartingPoint);
                }

            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }



}