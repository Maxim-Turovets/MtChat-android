package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;

public class SetPartnerAgeActivity extends AppCompatActivity {
    Button search;
    EditText from;
    EditText to;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_partner_age);

        search = (Button) findViewById(R.id.btnSetPartnerAgeSearch);
        from = (EditText) findViewById(R.id.inpSetPartnerAgeFrom);
        to = (EditText) findViewById(R.id.inpSetPartnerAgeTo);
        EchoWebSocketListener.setPartnerAgeActivity =this;
    }


    public void sendInfoToServer(View view) {

        StaticModels.interlocutorInfo.setAgeFrom(from.getText().toString());
        StaticModels.interlocutorInfo.setAgeTo(to.getText().toString());
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
    }

    public void goToChat()
    {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}