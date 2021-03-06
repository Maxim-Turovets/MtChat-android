package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;


public class SoundSwitchServise {

    private Switch aSwitch;


    public SoundSwitchServise(Switch imageMessageSwitch , final Context context) {
        this.aSwitch = imageMessageSwitch;
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StaticModels.setting.setSound(true);
                    SettingInfo.setSetting(context);
                }
                if (isChecked == false) {
                    StaticModels.setting.setSound(false);
                    SettingInfo.setSetting(context);
                }
            }
        });
    }





}
