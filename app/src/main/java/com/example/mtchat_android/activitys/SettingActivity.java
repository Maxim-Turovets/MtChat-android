package com.example.mtchat_android.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mtchat_android.R;




public class SettingActivity extends AppCompatActivity {

    View view ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

   view = findViewById(R.id.settings);
   //     view.setBackground(ContextCompat.getDrawable(this, R.drawable.gender_active_drawable));


    }
}
