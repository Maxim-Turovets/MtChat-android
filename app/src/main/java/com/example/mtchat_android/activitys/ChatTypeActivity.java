package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.mtchat_android.R;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;
import com.example.mtchat_android.serverobjects.ConnectInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;

public class ChatTypeActivity extends AppCompatActivity {


    private ImageButton privateBtn;
    private ImageButton generalBtn;
    private MotionLayout chatTypeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_type_layout);

        // init
        privateBtn = findViewById(R.id.privateBtn);
        generalBtn = findViewById(R.id.generalBtn);
        chatTypeLayout = findViewById(R.id.chat_type_container);

        chatTypeLayout.transitionToStart();
        chatTypeLayout.transitionToEnd();
    }


    public void privateBtnPress(View view) {
        // active
        privateBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));

        // init static object

        StaticModels.connectInfo = new ConnectInfo();
        StaticModels.connectInfo.setObjectType("ConnectInfo");
        StaticModels.connectInfo.setChatType("pair");
        StaticModels.isPrivateChat = true;

        if (StaticModels.setting.isGoToChat()) {
                StaticModels.userInfo = SettingInfo.getUserData(this);
                this.finish();
                Intent intent = new Intent(this, InterlocutorInfoActivity.class);
                startActivity(intent);
        }
        else if (StaticModels.setting.isRememberMyData()) {
            StaticModels.userInfo = SettingInfo.getUserData(this);
            this.finish();
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        }
        else {
            this.finish();
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        }


    }

    public void generalBtnPress(View view) {
        // active
        generalBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));


        // test
        StaticModels.connectInfo = new ConnectInfo();
        StaticModels.connectInfo.setObjectType("ConnectInfo");
        StaticModels.connectInfo.setChatType("pair");
        StaticModels.isPrivateChat = false;
        StartSocketConnection.startSocketConnection("chat");
        StartSocketConnection.sendTestJson();



        this.finish();
        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        System.exit(0);
    }
}
