package com.example.mtchat_android.activitys;

import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.MergedMessage;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.IfRoomCreated;
import com.example.mtchat_android.serverobjects.ImageCanSend;
import com.example.mtchat_android.serverobjects.InterlocutorTyping;
import com.example.mtchat_android.serverobjects.Message;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public  class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    public static ChatActivity chatActivity;
    public static SetPartnerAgeActivity setPartnerAgeActivity;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        if(objectInfo(text).toString().equals("ImageCanSend"))
        {
            ImageCanSend imageCanSend = new ImageCanSend();
            imageCanSend =(ImageCanSend) ObjectType.getObject(text, imageCanSend);

            if(imageCanSend.isAvailable())
            {
                chatActivity.hideButtonImageSend();

                Message myMessage = new Message();
                myMessage.setName("fict");
                myMessage.setObjectType("Message");
                myMessage.setText("you are allowed to send image");
                myMessage.setTime("");
                MergedMessage mergedMessage = new MergedMessage(myMessage);
                chatActivity.onMessage(mergedMessage);
            }
            if(imageCanSend.isAvailable()==false)
            {
                chatActivity.showButtonImageSend();

                Message myMessage = new Message();
                myMessage.setName("fict");
                myMessage.setObjectType("Message");
                myMessage.setText("you are not allowed to send image");
                myMessage.setTime("");
                MergedMessage mergedMessage = new MergedMessage(myMessage);
                chatActivity.onMessage(mergedMessage);
            }

        }
        if(objectInfo(text).toString().equals("InterlocutorTyping"))
        {
            InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
            interlocutorTyping =(InterlocutorTyping)ObjectType.getObject(text, interlocutorTyping);

            if(interlocutorTyping.isTyping()) {
                chatActivity.showPersonTyping();

            }

            if(interlocutorTyping.isTyping()==false)
                chatActivity.hidePersonTyping();

        }
        if(objectInfo(text).toString().equals("IfRoomDeleted"))
        {
            chatActivity.goToChatClose();
        }

        if(objectInfo(text).toString().equals("IfRoomCreated"))
        {
            IfRoomCreated ifRoomCreated = new IfRoomCreated("z");
            ifRoomCreated = (IfRoomCreated) ObjectType.getObject(text,ifRoomCreated);
            StaticModels.interlocutorName = ifRoomCreated.getNameInterlocutor();


            Message myMessage = new Message();
            myMessage.setName("fict");
            myMessage.setObjectType("Message");
            myMessage.setText(StaticModels.interlocutorName + " joined the chat");
            myMessage.setTime("");
            MergedMessage mergedMessage = new MergedMessage(myMessage);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(setPartnerAgeActivity!=null)
                setPartnerAgeActivity.goToChat();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
              chatActivity.onMessage(mergedMessage);


        }

       if(objectInfo(text).toString().equals("Message"))
       {
            Message tempMessage = new Message();
            tempMessage = (Message)ObjectType.getObject(text,tempMessage);
           MergedMessage mergedMessage = new MergedMessage(tempMessage);
            chatActivity.onMessage(mergedMessage);
            StaticModels.messageTime=tempMessage.getTime();
       }

    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {

        ImageMessage imageMessage = new ImageMessage(bytes.toByteArray(), false);
        imageMessage.setByteArray(bytes.toByteArray());
        MergedMessage mergedMessage = new MergedMessage(imageMessage);
        chatActivity.onMessage(mergedMessage);
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
