package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;
import com.example.mtchat_android.toasts.ToastAlert;

public class GoToChatSwitchServise {
    private Switch aSwitch;
    private Context context;


    public GoToChatSwitchServise(Switch imageMessageSwitch , final Context context) {
        this.aSwitch = imageMessageSwitch;
        this.context = context;
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(StaticModels.setting.isRememberMyData()&&StaticModels.setting.isRememberInterlocutor()) {
                        StaticModels.setting.setGoToChat(true);
                        SettingInfo.setSetting(context);
                    }
                    else {
                        ToastAlert.toastAlert(context,"\n" +
                                "you have not saved data");
                        aSwitch.setChecked(false);
                    }
                }
                if (isChecked == false) {
                    StaticModels.setting.setGoToChat(false);
                    SettingInfo.setSetting(context);
                }
            }
        });
    }
}
