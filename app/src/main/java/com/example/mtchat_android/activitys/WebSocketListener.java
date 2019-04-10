package com.example.mtchat_android.activitys;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.ResponseServer;
import com.example.mtchat_android.serverobjects.IfRoomCreated;
import com.example.mtchat_android.serverobjects.IfRoomDeleted;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

 class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        ResponseServer.setResponseServerString(text.toString());
        IfRoomCreated k = new IfRoomCreated("z");
        k = (IfRoomCreated) ObjectType.getObject(text,k);
        //ResponseServer.responseServerString = k.getNameInterlocutor();
        //String s = ResponseServer.getResponseServerString();
       // System.out.print(text);
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
