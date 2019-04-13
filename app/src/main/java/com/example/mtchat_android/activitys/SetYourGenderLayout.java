package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.UserInfo;

public class SetYourGenderLayout extends AppCompatActivity {


    Button genderMale;
    Button genderFemale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_your_gender);

        genderMale = (Button)findViewById(R.id.btnSetYourGenderMale);
        genderFemale = (Button)findViewById(R.id.btnSetYourGenderFemale);

    }


    public  void goToSetAgeMale(View view)
    {
        StaticModels.userInfo.setGender("male");
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public  void goToSetAgeFemale(View view)
    {
        StaticModels.userInfo.setGender("female");
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
