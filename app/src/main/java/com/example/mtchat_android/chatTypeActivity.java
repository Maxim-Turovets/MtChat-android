package com.example.mtchat_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class chatTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_type_layout);
    }


    public  void goToChat(View view)
    {
        Intent intent = new Intent(this,chatActivity.class);
        startActivity(intent);
    }
}
