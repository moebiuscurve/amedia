package com.example.naveenk.amedia;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by naveenk on 16/5/15.
 */
public class SimpleBrowser extends Activity implements OnClickListener {

    EditText url;
    WebView ourBrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.getSettings().setUseWideViewPort(true);
        ourBrow.getSettings().setBuiltInZoomControls(true);
        ourBrow.setWebViewClient(new WebViewClient());

        ourBrow.loadUrl("http://moebiuscurve.livejournal.com");

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button forward = (Button) findViewById(R.id.bForward);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        clearHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrow.loadUrl(theWebsite);
                break;
            case R.id.bBack:
                if(ourBrow.canGoBack())
                ourBrow.goBack();
                break;
            case R.id.bForward:
                if(ourBrow.canGoForward())
                    ourBrow.goForward();
                break;
            case R.id.bRefresh:
                ourBrow.reload();
                break;
            case R.id.bHistory:
                ourBrow.clearHistory();
                break;
        }
    }
}
