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

public class SetYourNameActivity extends AppCompatActivity {


    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_your_name_layout);

        text = (TextView)findViewById(R.id.inpSetName);

    }


    public  void goToSetGender(View view)
    {
        StaticModels.userInfo = new UserInfo();
        StaticModels.userInfo.setObjectType("UserInfo");
        StaticModels.userInfo.setName(text.getText().toString());

        Intent intent = new Intent(this, SetYourGenderLayout.class);
        startActivity(intent);
    }
}
