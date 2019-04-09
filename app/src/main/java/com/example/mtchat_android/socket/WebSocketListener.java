package com.example.mtchat_android.socket;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Hello, it's SSaurel !");
        webSocket.send("What's up ?");
        webSocket.send(ByteString.decodeHex("deadbeef"));
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.print("Receiving : " + text);
    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.print("Receiving bytes : " + bytes.hex());
    }
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.print("Closing : " + code + " / " + reason);
    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.print("Error : " + t.getMessage());
    }
}
