package com.example.mtchat_android.activitys.settingActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.settingActivity.switchServises.GoToChatSwitchServise;
import com.example.mtchat_android.activitys.settingActivity.switchServises.RememberInterlocutorSwitchServise;
import com.example.mtchat_android.activitys.settingActivity.switchServises.RememberMyDataSwitchServise;
import com.example.mtchat_android.activitys.settingActivity.switchServises.SoundSwitchServise;
import com.example.mtchat_android.activitys.settingActivity.switchServises.VibrationSwitchServise;
import com.example.mtchat_android.models.StaticModels;


public class SettingActivity extends AppCompatActivity {

    View view;

    Switch switchSound;
    Switch switchgoToChat;
    Switch switchVibration;
    Switch switchRememberMyData;
    Switch switchRememberInterlocutor;


    View settingData;
    View settingChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        view = findViewById(R.id.settings);
        settingData = findViewById(R.id.settingData);
        settingChat = findViewById(R.id.settingChat);
        switchSound = findViewById(R.id.settingSoundSwitch);
        switchgoToChat = findViewById(R.id.settingGoToChatSwitch);
        switchVibration = findViewById(R.id.settingVibrationSwitch);
        switchRememberMyData = findViewById(R.id.settingRememberNyDataSwitch);
        switchRememberInterlocutor = findViewById(R.id.settingRememberInterlocutorSwitch);

        //    view.setBackground(ContextCompat.getDrawable(this, R.drawable.gender_active_drawable));

        if(!StaticModels.isPrivateChat)
        {
            settingChat.setVisibility(View.GONE);
            settingData.setVisibility(View.GONE);
        }
        switchSound.setChecked(StaticModels.setting.isSound());
        switchgoToChat.setChecked(StaticModels.setting.isGoToChat());
        switchVibration.setChecked(StaticModels.setting.isVibration());
        switchRememberMyData.setChecked(StaticModels.setting.isRememberMyData());
        switchRememberInterlocutor.setChecked(StaticModels.setting.isRememberInterlocutor());

        new SoundSwitchServise(switchSound, this);
        new GoToChatSwitchServise(switchgoToChat, this);
        new VibrationSwitchServise(switchVibration, this);
        new RememberMyDataSwitchServise(switchRememberMyData, this,switchgoToChat);
        new RememberInterlocutorSwitchServise(switchRememberInterlocutor, this,switchgoToChat);
    }


}
