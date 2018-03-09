package com.project.jingdong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.project.jingdong.R;

public class WebViewLoad extends AppCompatActivity {

    private static final String TAG = "web";
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_load);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        initView();
        mWeb.loadUrl(url);
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        //声明WebSettings子类
        WebSettings webSettings = mWeb.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);


    }
}
