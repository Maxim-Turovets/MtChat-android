package com.example.mtchat_android.activitys.interlocutionActivity.keyboards;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorTyping;

public class KeyboardServise {



    private int maxim = 0;

    /**
     * If the keyboard is active then send a request to the server
     * replace the button "text" on the button "image"
     **/
    public KeyboardServise(final EditText editText, final ImageButton imageMessageButton, final ImageButton textMessageButton, final boolean showButton) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = editText.getText().length();

                if (size > 0)
                    if (maxim == 0) {
                        InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                        interlocutorTyping.setTyping(true);
                        interlocutorTyping.setName(StaticModels.userInfo.getName());
                        StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                        imageMessageButton.setVisibility(View.GONE);
                        textMessageButton.setVisibility(View.VISIBLE);
                        maxim++;
                    }
                if (size == 0) {
                    maxim = 0;
                    InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                    interlocutorTyping.setTyping(false);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                    if (showButton) {
                        imageMessageButton.setVisibility(View.VISIBLE);
                        textMessageButton.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }



}
