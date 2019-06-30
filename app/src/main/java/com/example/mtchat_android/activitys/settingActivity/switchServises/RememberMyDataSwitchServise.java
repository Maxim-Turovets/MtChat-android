package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;

public class RememberMyDataSwitchServise {
    private Switch aSwitch;
    private Context context;


    public RememberMyDataSwitchServise(Switch imageMessageSwitch , final Context context, final Switch goToChat) {
        this.aSwitch = imageMessageSwitch;
        this.context = context;
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StaticModels.setting.setRememberMyData(true);
                    SettingInfo.setSetting(context);
                    SettingInfo.setUserData(context);
                }
                if (isChecked == false) {
                    StaticModels.setting.setRememberMyData(false);
                    goToChat.setChecked(false);
                    StaticModels.setting.setGoToChat(false);
                    SettingInfo.setSetting(context);
                }
            }
        });
    }
}
