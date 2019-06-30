package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;

public class RememberInterlocutorSwitchServise {
    private Switch aSwitch;
    private Switch goToChat;
    private Context context;


    public RememberInterlocutorSwitchServise(Switch imageMessageSwitch , final Context context, final Switch goToChat) {
        this.aSwitch = imageMessageSwitch;
        this.goToChat = goToChat;
        this.context = context;
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StaticModels.setting.setRememberInterlocutor(true);
                    SettingInfo.setSetting(context);
                    SettingInfo.setInterlocutorData(context);
                }
                if (isChecked == false) {
                    StaticModels.setting.setRememberInterlocutor(false);
                    goToChat.setChecked(false);
                    StaticModels.setting.setGoToChat(false);
                    SettingInfo.setSetting(context);
                }
            }
        });
    }
}
