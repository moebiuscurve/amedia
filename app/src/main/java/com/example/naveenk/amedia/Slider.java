package com.example.naveenk.amedia;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

/**
 * Created by naveenk on 16/5/15.
 */
public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener {
    SlidingDrawer sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);
        Button handle1 = (Button) findViewById(R.id.handle1);
        Button handle2 = (Button) findViewById(R.id.handle2);
        Button handle3 = (Button) findViewById(R.id.handle3);
        Button handle4 = (Button) findViewById(R.id.handle4);
        CheckBox checkbox = (CheckBox) findViewById(R.id.cbSlidable);
        sd = (SlidingDrawer) findViewById(R.id.slidingD);
        sd.setOnDrawerOpenListener(this);
        checkbox.setOnCheckedChangeListener(this);
        handle1.setOnClickListener(this);
        handle2.setOnClickListener(this);
        handle3.setOnClickListener(this);
        handle4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.handle1:
                sd.open();
                break;
            case R.id.handle2:
                sd.close();
                break;
            case R.id.handle3:
                sd.toggle();
                break;
            case R.id.handle4:
                sd.close();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.isChecked()){
            sd.lock();
        }else{
            sd.unlock();
        }
    }

    @Override
    public void onDrawerOpened() {
        MediaPlayer mp = MediaPlayer.create(this,R.raw.explosion);
        mp.start();
    }
}
