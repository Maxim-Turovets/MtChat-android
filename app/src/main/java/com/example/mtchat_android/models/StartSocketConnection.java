package com.example.mtchat_android.models;


import com.example.mtchat_android.activitys.EchoWebSocketListener;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.serverobjects.ConnectInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class StartSocketConnection {

    public static Request request;
    public static EchoWebSocketListener listener;
    public static OkHttpClient client;
    public static WebSocket webSocket;
    public static String interlocutorName;

    public static void startSocketConnection() {
        client = new OkHttpClient();
        request = new Request.Builder().url("ws://77.47.224.135:8080/sock/chat").build();
        listener = new EchoWebSocketListener();
        webSocket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    public static void sendTestJson(){
        StaticModels.userInfo = new UserInfo();
        StaticModels.userInfo.setName("Android");
        ConnectInfo connectInfo = new ConnectInfo();
        connectInfo.setObjectType("ConnectInfo");
        connectInfo.setChatType("pair");
        StartSocketConnection.webSocket.send(ObjectType.getJson(connectInfo));

        UserInfo userInfo = new UserInfo();
        userInfo.setObjectType("UserInfo");
        userInfo.setAge("1");
        userInfo.setGender("male");
        userInfo.setName("User Test");
        userInfo.setVoiceMessage(false);
        StartSocketConnection.webSocket.send(ObjectType.getJson(userInfo));

        InterlocutorInfo info = new InterlocutorInfo();
        info.setAgeFrom("1");
        info.setAgeTo("100");
        info.setGender("male");
        info.setObjectType("InterlocutorInfo");
        StartSocketConnection.webSocket.send(ObjectType.getJson(info));

    }


}
