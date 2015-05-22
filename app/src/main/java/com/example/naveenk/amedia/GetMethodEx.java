package com.example.naveenk.amedia;

import android.util.Log;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by naveenk on 22/5/15.
 * This Class does not work and is derieved from New Boston Lectures that I am following
 * since Honeycomb you are not supposed to execute network access on your app's main thread (to improve responsiveness).
 *Therefore I made modifications to New Boston source and implemented AsyncTask through an inner private class in HttpExample.java
 */
public class GetMethodEx {
    public String getInternetData() throws Exception {
        BufferedReader in = null;
        String data = null;
        HttpURLConnection  urlConnection=null;
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
        }finally {
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
    }
}
