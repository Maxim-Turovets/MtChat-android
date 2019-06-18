package com.example.mtchat_android.models;

import android.app.Activity;

import com.example.mtchat_android.activitys.LoadingAnimationActivity;
import com.example.mtchat_android.serverobjects.ConnectInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;

public class StaticModels {

    public  static UserInfo userInfo;
    public  static String interlocutorName;
    public  static InterlocutorInfo interlocutorInfo;
    public  static ConnectInfo connectInfo;
    public  static String messageTime;
    public  static LoadingAnimationActivity loadingAnimationActivity;
    public  static StringBuffer imageStringBuffer;
    public  static boolean isAnonimGender;
    public  static Setting setting = new Setting();
    public  static boolean isPrivateChat;
}
