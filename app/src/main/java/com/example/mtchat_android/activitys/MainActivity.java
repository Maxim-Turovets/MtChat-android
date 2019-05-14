package com.example.mtchat_android.activitys;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mtchat_android.R;




public class  MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.finish();
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);


    }


}
