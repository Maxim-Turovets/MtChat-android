package com.example.mtchat_android.activitys;

import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.IfRoomCreated;
import com.example.mtchat_android.serverobjects.Message;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public  class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    public static ChatActivity chatActivity;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {


        if(objectInfo(text).toString().equals("IfRoomCreated"))
        {
            StaticModels.ifRoomCreated = new IfRoomCreated("z");
            StaticModels.ifRoomCreated = (IfRoomCreated) ObjectType.getObject(text,StaticModels.ifRoomCreated);
            StartSocketConnection.interlocutorName = StaticModels.ifRoomCreated.getNameInterlocutor();


            Message myMessage = new Message();
            myMessage.setName("fict");
            myMessage.setObjectType("Message");
            myMessage.setText(StaticModels.ifRoomCreated.getNameInterlocutor());
            myMessage.setTime("");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
              chatActivity.onMessage(myMessage);

        }

       if(objectInfo(text).toString().equals("Message"))
       {
            Message tempMessage = new Message();
            tempMessage = (Message)ObjectType.getObject(text,tempMessage);
            chatActivity.onMessage(tempMessage);
            StaticModels.messageTime=tempMessage.getTime();
       }

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



     /**
      * For the first JSON field, take the object type.
      * @param json (String)
      * @return StringBugger (name object)
      */
     private StringBuffer objectInfo(String json) {
         int lastIndex =json.indexOf("objectType");
         lastIndex+=13;
         StringBuffer returnJson = new StringBuffer();

         for (int i = lastIndex; i < json.length(); i++) {
             if (json.charAt(i) == '\"') {
                 return returnJson;
             }
             else {
                 returnJson.append(json.charAt(i));
             }
         }
         returnJson.deleteCharAt(0);
         return returnJson;
     }
}
