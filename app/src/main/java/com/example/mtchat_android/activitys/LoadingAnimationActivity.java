package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingAnimationActivity extends AppCompatActivity {

    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadind_animation_layout);

        StaticModels.loadingAnimationActivity = this;
        myBrowser = (WebView) findViewById(R.id.web_view);
        myBrowser.loadUrl("file:///android_asset/animation/animation.html");


        class UpdateTimeTask extends TimerTask {
            public void run() {

            }
        }



    }


    public void goToChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}


