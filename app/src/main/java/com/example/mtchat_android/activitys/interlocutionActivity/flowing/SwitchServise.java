package com.example.mtchat_android.activitys.interlocutionActivity.flowing;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.serverobjects.ImageCanSend;

public class SwitchServise {

    private Switch imageMessageSwitch;

    /**
     *
     * @param imageMessageSwitch
     * On or off switch
     */
    public SwitchServise(Switch imageMessageSwitch) {
        this.imageMessageSwitch = imageMessageSwitch;

        imageMessageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(false);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                }
                if (isChecked == false) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(true);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                }
            }
        });
    }
}
