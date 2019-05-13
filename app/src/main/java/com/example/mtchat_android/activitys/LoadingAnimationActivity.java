package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.Timer;
import java.util.TimerTask;

import me.itangqi.waveloadingview.WaveLoadingView;

public class LoadingAnimationActivity extends AppCompatActivity {


    int countClickedBackButton = 0;
    int countProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

//        this.finish();
//        Intent intent = new Intent(this, ChatTypeActivity.class);
//        startActivity(intent);

//        final WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
//        mWaveLoadingView.startAnimation();

        GeometricProgressView progressView = (GeometricProgressView) findViewById(R.id.progressView);
        progressView.setNumberOfAngles(8);
        progressView.setDuration(1000);


        new CountDownTimer(50_000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countProgress+=2;
//                mWaveLoadingView.setProgressValue(countProgress);
            }
            @Override
            public void onFinish() {

            }
        }.start();

    }



    public void goToChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        countClickedBackButton++;
        if(countClickedBackButton==2)
        {
            this.finish();
            StartSocketConnection.webSocket.close(4999,"Back to intelocutor info activity");
            Intent intent = new Intent(this, InterlocutorInfoActivity.class);
            startActivity(intent);
        }
        Toast toast = Toast.makeText(this, "Press again to back", Toast.LENGTH_SHORT);
        toast.show();

        new CountDownTimer(2_000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                countClickedBackButton=0;
            }
        }.start();

    }
}


