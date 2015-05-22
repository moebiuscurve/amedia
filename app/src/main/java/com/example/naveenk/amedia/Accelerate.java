package com.example.naveenk.amedia;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by naveenk on 22/5/15.
 */
public class Accelerate extends Activity implements SensorEventListener{

    float x,y,sensorX,sensorY;
    Bitmap ball;
    SensorManager sm;
    Sensor sen;
    MyBringBackSurface ourSurfaceView;

    public class MyBringBackSurface extends SurfaceView implements Runnable{

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
            //ourThread = new Thread(this);
            //ourThread.start();
        }


        public void pause(){
            isRunning=false;
            while (true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread=null;
        }

        public void resume(){
            isRunning=true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while(isRunning){
                if(!ourHolder.getSurface().isValid()) continue;
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);
                float centerX = canvas.getWidth()/2;
                float centerY = canvas.getHeight()/2;
                canvas.drawBitmap(ball, centerX + sensorX + 20, centerY + sensorY +20,null);
                Log.i("TAG", "x="+(centerX + sensorX + 20) + " y=" + (centerY + sensorY +20));
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            sen = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            //sen = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener(this,sen,SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        x = y = sensorX = sensorY =0;
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        ourSurfaceView.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,sen,SensorManager.SENSOR_DELAY_NORMAL);
        ourSurfaceView.resume();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
