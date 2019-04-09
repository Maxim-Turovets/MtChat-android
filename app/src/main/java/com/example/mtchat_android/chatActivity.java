package com.example.mtchat_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class chatActivity extends AppCompatActivity {



    private String channelID = "CHANNEL_ID_FROM_YOUR_SCALEDRONE_DASHBOARD";
    private String roomName = "observable-room";
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is where we write the mesage
        editText = (EditText) findViewById(R.id.editText);
    }



    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {

            editText.getText().clear();
        }
    }


}
