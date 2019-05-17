package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.ConnectInfo;
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



    public  void privateBtnPress(View view)  {
        // active
        privateBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));

        // socket send message

        StaticModels.connectInfo = new ConnectInfo();
        StaticModels.connectInfo.setObjectType("ConnectInfo");
        StaticModels.connectInfo.setChatType("pair");

        this.finish();
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);



    }

    public  void generalBtnPress(View view) {
        // active
        generalBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));

        // test
        StartSocketConnection.startSocketConnection();
        StartSocketConnection.sendTestJson();
        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
            this.finish();
            System.exit(0);
    }
}
