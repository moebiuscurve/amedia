package com.example.naveenk.amedia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

/**
 * Created by naveenk on 18/5/15.
 */
public class SharedPrefs extends Activity implements OnClickListener {

    EditText sharedData;
    TextView dataResults;
    SharedPreferences someData;
    public static String filename ="MySharedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefereneces);
        setupVariables();
        someData = getSharedPreferences(filename,0);
    }

    protected void setupVariables() {
        Button save =(Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSave:
                String stringData = sharedData.getText().toString();
                Editor editor = someData.edit();
                editor.putString("sharedString",stringData);
                editor.commit();
                break;
            case R.id.bLoad:
                someData = getSharedPreferences(filename,0);
                String dataReturned = someData.getString("sharedString","Couldn't Load Data");
                dataResults.setText(dataReturned);
                break;
        }
    }

}
