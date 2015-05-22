package com.example.naveenk.amedia;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by naveenk on 22/5/15.
 * since Honeycomb you are not supposed to execute network access on your app's main thread (to improve responsiveness).
 * Therefore I made modifications to New Boston source and implemented AsyncTask through an inner private class
 */
public class HttpExample extends Activity {

    TextView httpStuff;
    String data = null;
    URL myURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        new loadURLStuff().execute(data);
        //Log.i("TAG", returned);

    }

    private class loadURLStuff extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... params) {
            BufferedReader in = null;
            HttpURLConnection urlConnection=null;
            URL url;
            try{
                //url = new URL("http://www.android.com/");
                //urlConnection = (HttpURLConnection) url.openConnection();
                //in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                HttpClient client = new DefaultHttpClient();
                URI website = new URI("http://www.android.com/");
                HttpGet request = new HttpGet();
                request.setURI(website);
                HttpResponse response = client.execute(request);
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String l ="";
                String nl =System.getProperty("line.separator");
                while((l=in.readLine())!= null){
                    sb.append(l+nl);
                    //Log.i("InternetData",l.toString());
                }
                in.close();
                data = sb.toString();
                return  data;
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            } finally {
                //urlConnection.disconnect();
                if(in != null){
                    try{
                        in.close();
                        return data;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            httpStuff.setText(s);
        }
    }
}
