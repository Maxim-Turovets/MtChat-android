package com.example.mtchat_android.activitys.settingActivity.switchServises;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;
import com.example.mtchat_android.toasts.ToastAllert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;


public class SoundSwitchServise {

    private Switch aSwitch;
    private  Context context;


    public SoundSwitchServise(Switch imageMessageSwitch , final Context context) {
        this.aSwitch = imageMessageSwitch;
        this.context = context;
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
