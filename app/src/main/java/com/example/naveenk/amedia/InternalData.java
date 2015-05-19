package com.example.naveenk.amedia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by naveenk on 18/5/15.
 */
public class InternalData extends Activity implements OnClickListener {

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos;
    String FILENAME = "InternalString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefereneces);
        setupVariables();
    }

    protected void setupVariables() {
        Button save =(Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSave:
                String data=sharedData.getText().toString();

                //Saving data via File
                /*
                File f = new File(FILENAME);
                try {
                    fos = new FileOutputStream(f);
                    //write some data before closing
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */
                try {
                    fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*
                File f = getDatabasePath(FILENAME);

                if (f != null)
                    Log.i("TAG", f.getAbsolutePath());
                */
                break;
            case R.id.bLoad:
                new loadSomeStuff().execute(FILENAME);
                break;
        }
    }

        public class loadSomeStuff extends AsyncTask<String, Integer,String>{

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(InternalData.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMax(100);
                dialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String collected =null;
                FileInputStream fis = null;

                for(int i=0; i<20; i++){
                    publishProgress(5);
                }
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

                try {
                    fis = openFileInput(FILENAME);
                    byte[] dataArray = new byte[fis.available()];
                    while(fis.read(dataArray)!=-1){
                        collected =new String(dataArray);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                        //dataResults.setText(collected);
                        return collected;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... progress) {
                //super.onProgressUpdate(progress);
                dialog.incrementProgressBy(progress[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                //super.onPostExecute(result);
                dataResults.setText(result);
            }
        }

}
