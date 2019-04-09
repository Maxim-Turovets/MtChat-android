package com.example.mtchat_android.models;

import okhttp3.WebSocket;

public class ResponseServer {

    public  static WebSocket webSocket;
    public  static String  responseServerString;

    public static String getResponseServerString() {
        return responseServerString;
    }

    public static void setResponseServerString(String responseServerString) {
        ResponseServer.responseServerString = responseServerString;
    }
}
