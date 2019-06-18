package com.example.mtchat_android.activitys.settingActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.mtchat_android.R;
import com.example.mtchat_android.models.StaticModels;


public class SettingActivity extends AppCompatActivity {

    View view ;
    Switch switchSound;
    Switch switchVibration;
    Switch switchRememberMyData;
    Switch switchRememberInterlocutor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

   view = findViewById(R.id.settings);
   switchSound = findViewById(R.id.settingSoundSwitch);
   switchVibration = findViewById(R.id.settingVibrationSwitch);
   switchRememberMyData = findViewById(R.id.settingRememberNyDataSwitch);
   switchRememberInterlocutor = findViewById(R.id.settingRememberInterlocutorSwitch);

   //     view.setBackground(ContextCompat.getDrawable(this, R.drawable.gender_active_drawable));

        switchSound.setChecked(StaticModels.setting.isSound());
        switchVibration.setChecked(StaticModels.setting.isVibration());

        new SoundSwitchServise(switchSound,this);
        new VibrationSwitchServise(switchVibration,this);
    }


}
