package com.example.mtchat_android.activitys;

import android.content.Intent;

import android.os.CountDownTimer;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mtchat_android.R;




public class  MainActivity extends AppCompatActivity {


    MotionLayout greetingLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting_layout);

        // Init
        greetingLayout = findViewById(R.id.greeting_container);


        new CountDownTimer(1_700,1_500) {
            @Override
            public void onTick(long millisUntilFinished) {
                greetingLayout.transitionToEnd();
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
