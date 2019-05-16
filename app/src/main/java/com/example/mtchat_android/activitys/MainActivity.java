package com.example.mtchat_android.activitys;

import android.content.Intent;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mtchat_android.R;




public class  MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting_layout);

        new CountDownTimer(1_500,1220) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                nextLayout();
            }
        }.start();



    }

    public  void  nextLayout()
    {
        this.finish();
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }


}
