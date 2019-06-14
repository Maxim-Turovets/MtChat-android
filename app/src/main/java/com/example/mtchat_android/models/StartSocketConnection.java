package com.example.mtchat_android.models;


import com.example.mtchat_android.activitys.EchoWebSocketListener;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.serverobjects.ConnectInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class StartSocketConnection {

    private static Request request;
    private static EchoWebSocketListener listener;
    private static OkHttpClient client;
    public static WebSocket webSocket;


    public static void startSocketConnection(String ip) {
        client = new OkHttpClient();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        //18.224.252.15
        request = new Request.Builder().url("ws://18.224.252.15:8080/sock/"+ip).build();
        listener = new EchoWebSocketListener();
        webSocket = okHttpClient.newWebSocket(request, listener);
           }

    public static void sendTestJson(){
        ConnectInfo connectInfo = new ConnectInfo();
        connectInfo.setObjectType("ConnectInfo");
        connectInfo.setChatType("pair");
        StartSocketConnection.webSocket.send(ObjectType.getJson(connectInfo));

        UserInfo userInfo = new UserInfo();
        userInfo.setObjectType("UserInfo");
        userInfo.setAge("1");
        userInfo.setGender("male");
        userInfo.setName(getRandomMane());
        userInfo.setVoiceMessage(false);

        StaticModels.userInfo = new UserInfo();
        StaticModels.userInfo.setObjectType("UserInfo");
        StaticModels.userInfo.setAge("1");
        StaticModels.userInfo.setGender("male");
        StaticModels.userInfo.setName(getRandomMane());
        StaticModels.userInfo.setVoiceMessage(false);

        StartSocketConnection.webSocket.send(ObjectType.getJson(userInfo));


        InterlocutorInfo info = new InterlocutorInfo();
        info.setAgeFrom("1");
        info.setAgeTo("100");
        info.setGender("male");
        info.setObjectType("InterlocutorInfo");

        StaticModels.interlocutorInfo = new InterlocutorInfo();
        StaticModels.interlocutorInfo.setAgeFrom("1");
        StaticModels.interlocutorInfo.setAgeTo("100");
        StaticModels.interlocutorInfo.setGender("male");
        StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");

        StartSocketConnection.webSocket.send(ObjectType.getJson(info));



    }

    private  static String getRandomMane()
    {
        List <String> nameList = new ArrayList<>();
        nameList.add("Снежный барс");
        nameList.add("Леопард");
        nameList.add("Лев");
        nameList.add("Тигр");
        nameList.add("Гепард");
        nameList.add("Пума");
        nameList.add("Пантера");
        nameList.add("Рысь");
        nameList.add("Ягуар");
        nameList.add("java иди на***");

        double item = Math.random()*(nameList.size());
        return nameList.get((int)item);

    }


}
