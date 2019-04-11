package com.example.mtchat_android.activitys;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class StartSocketConnection {

    public static Request request;
    public static EchoWebSocketListener listener;
    public static OkHttpClient client;
    public static WebSocket webSocket;

    public static void startSocketConnection() {
        client = new OkHttpClient();
        request = new Request.Builder().url("ws://77.47.224.135:8080/sock/chat").build();
        listener = new EchoWebSocketListener();
        webSocket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }


}
