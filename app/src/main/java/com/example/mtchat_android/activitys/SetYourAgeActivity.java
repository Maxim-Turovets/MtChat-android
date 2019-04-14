package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.UserInfo;

public class SetYourAgeActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_your_age);

        text = (TextView)findViewById(R.id.inpSetAge);

    }


    public  void goToSetPartnerGender(View view)
    {
        StaticModels.userInfo.setAge(text.getText().toString());
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));

        Intent intent = new Intent(this, SetPartnerGenderActivity.class);
        startActivity(intent);
    }
}
