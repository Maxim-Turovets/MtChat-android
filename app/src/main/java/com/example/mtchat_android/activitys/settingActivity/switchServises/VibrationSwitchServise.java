package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;

public class VibrationSwitchServise {
    private Switch aSwitch;
    private Context context;


    public VibrationSwitchServise(Switch imageMessageSwitch , final Context context) {
        this.aSwitch = imageMessageSwitch;
        this.context = context;
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StaticModels.setting.setVibration(true);
                    SettingInfo.setSetting(context);
                }
                if (isChecked == false) {
                    StaticModels.setting.setVibration(false);
                    SettingInfo.setSetting(context);
                }
            }
        });
    }

}
