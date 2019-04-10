package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mtchat_android.R;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.ResponseServer;
import com.example.mtchat_android.serverobjects.ConnectInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;



public class  MainActivity extends AppCompatActivity {

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        Intent intent = new Intent(this, chatTypeActivity.class);
        startActivity(intent);

        startSocketConnection();


    }

    private void startSocketConnection() {
        Request request = new Request.Builder().url("ws://77.47.224.135:8080/sock/chat").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        ResponseServer.webSocket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }


    private void sendTestJson(){
        ConnectInfo connectInfo = new ConnectInfo();
        connectInfo.setObjectType("ConnectInfo");
        connectInfo.setChatType("pair");
        ResponseServer.webSocket.send(ObjectType.getJson(connectInfo));

        UserInfo userInfo = new UserInfo();
        userInfo.setObjectType("UserInfo");
        userInfo.setAge("1");
        userInfo.setGender("male");
        userInfo.setName("User Test");
        userInfo.setVoiceMessage(false);
        ResponseServer.webSocket.send(ObjectType.getJson(userInfo));

        InterlocutorInfo info = new InterlocutorInfo();
        info.setAgeFrom("1");
        info.setAgeTo("100");
        info.setGender("male");
        info.setObjectType("InterlocutorInfo");
        ResponseServer.webSocket.send(ObjectType.getJson(info));



    }


}



