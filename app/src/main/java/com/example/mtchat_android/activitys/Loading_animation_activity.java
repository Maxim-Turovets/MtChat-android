package com.example.mtchat_android.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.mtchat_android.R;

public class Loading_animation_activity extends AppCompatActivity {

    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadind_animation_layout);

        myBrowser = (WebView)findViewById(R.id.web_view);
        myBrowser.loadUrl("file:///android_asset/animation/animation.html");    }
}
