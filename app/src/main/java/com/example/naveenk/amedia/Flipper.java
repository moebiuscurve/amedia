package com.example.naveenk.amedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;
//import android.view.View;
//import android.view.View.OnClickListener;

/**
 * Created by naveenk on 17/5/15.
 */
//public class Flipper extends Activity implements OnClickListener{
public class Flipper extends Activity {
    ViewFlipper flippy;
    float initialX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        //flippy.setOnClickListener(this);
        flippy.setInAnimation(this, android.R.anim.fade_in);
        flippy.setOutAnimation(this,android.R.anim.fade_out);

        /* Automatic flipping
        flippy.setFlipInterval(500);
        flippy.startFlipping();
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if(initialX>finalX){
                    if(flippy.getDisplayedChild() == 3){
                        flippy.setDisplayedChild(0);
                        break;
                    }
                    flippy.showNext();
                }else{
                    if(flippy.getDisplayedChild()==0){
                        flippy.setDisplayedChild(3);
                        break;
                    }
                    flippy.showPrevious();
                }
                break;
        }

        return false;
    }
/*
    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
*/
}
