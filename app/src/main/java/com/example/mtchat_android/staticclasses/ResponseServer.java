package com.example.mtchat_android.staticclasses;

import android.content.Context;

import com.example.mtchat_android.models.MessageAdapter;
import com.example.mtchat_android.serverobjects.Message;

import java.util.ArrayList;

public class ResponseServer {

    public  static Context context;
    public  static String responseString;
    public  static ArrayList<Message> messageList = new ArrayList<>();
    public  static MessageAdapter messageAdapter;

    public static MessageAdapter getMessageAdapter() {
        return messageAdapter;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ResponseServer.context = context;
    }

    public static String getResponseString() {
        return responseString;
    }

    public static void setResponseString(String responseString) {
        ResponseServer.responseString = responseString;
    }

    public static void addMessageToList(Message message){
//        if(messageList==null)
//            messageList=new ArrayList<Message>();

        messageList.add(message);
    }

    public static ArrayList<Message> getMessageList() {
        return messageList;
    }


    public  static  String printMessage(){
        String ret = "";

        for (int i=0;i<messageList.size();i++)
        {
            ret+="; "+messageList.get(i).getText()+"\n";
        }

        return  ret;
    }

}
