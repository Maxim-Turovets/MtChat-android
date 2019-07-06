package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mtchat_android.R;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.ConnectInfo;

public class ChatCloseActivity extends AppCompatActivity {

    Button reconnect;
    Button goToMainMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_close_layout);

        reconnect = (Button)findViewById(R.id.btnChatCloseReconnect);
        goToMainMenu = (Button)findViewById(R.id.btnChatCloseGoToMainMenu);

    }


    public  void reconnectButtonPress(View view)
    {
        ConnectInfo templateConnectInfo = new ConnectInfo();
        templateConnectInfo.setObjectType("ConnectInfo");
        templateConnectInfo.setChatType("pair");

        StartSocketConnection.webSocket.send(ObjectType.getJson(templateConnectInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);
    }

    public  void goToMainMenu(View view)
    {
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }
//    @Override
//    public void onBackPressed() {
//        this.finish();
//        Intent intent = new Intent(this, ChatTypeActivity.class);
//        startActivity(intent);
//    }
}
