package com.zk.arouter_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = "/test/webview")
public class WebViewActivity extends AppCompatActivity {

    public static final String ROUTE_PATH = "/test/webview";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/a.html");


    }
}
