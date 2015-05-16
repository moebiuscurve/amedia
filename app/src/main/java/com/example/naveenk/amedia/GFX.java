package com.example.naveenk.amedia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

/**
 * Created by naveenk on 12/5/15.
 */
public class GFX extends Activity {

    MyBringBack ourView;
    WakeLock wL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //wake-lock
        PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");
        super.onCreate(savedInstanceState);
        wL.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wL.acquire();
    }
}
