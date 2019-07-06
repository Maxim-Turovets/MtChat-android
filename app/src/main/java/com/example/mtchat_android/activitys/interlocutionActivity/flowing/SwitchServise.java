package com.example.mtchat_android.activitys.interlocutionActivity.flowing;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mtchat_android.activitys.InterlocutorInfoActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.serverobjects.ImageCanSend;
import com.example.mtchat_android.toasts.ToastAlert;

public class SwitchServise {

    private Switch imageMessageSwitch;
    private Context context;

    /**
     *
     * @param imageMessageSwitch
     * On or off switch
     */
    public SwitchServise(Switch imageMessageSwitch,Context context) {
        this.imageMessageSwitch = imageMessageSwitch;
        this.context = context;

        imageMessageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(true);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                }
                if (isChecked == false) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(false);
                   StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                    blockSwitch(10);
                }
            }
        });
    }


    public void blockSwitch(int sec) {
        imageMessageSwitch.setEnabled(false);



        new Thread(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(10_000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        imageMessageSwitch.setEnabled(true);
                        ToastAlert.toastAlert(context,"Теперь вы можете опять открыть доступ для фото!",300);
                    }
                }.start();
            }
        }).run();





    }
}
