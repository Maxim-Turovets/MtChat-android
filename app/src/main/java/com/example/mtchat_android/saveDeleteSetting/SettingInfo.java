package com.example.mtchat_android.saveDeleteSetting;

import android.content.Context;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.Setting;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;
import com.example.mtchat_android.toasts.ToastAllert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import static android.content.Context.MODE_PRIVATE;

public class SettingInfo {

    private final static String FILE_NAME = "setting.txt";
    private final static String FILE_USER = "user.txt";
    private final static String FILE_INTERLOCUTOR = "interlocutor.txt";

    public static void setSetting(Context context){
        FileOutputStream fos = null;
        try {
            String text = ObjectType.getJson(StaticModels.setting);
            fos = context.openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(text.getBytes());
            ToastAllert.toatallert(context,"setting is Save");
        }
        catch(IOException ex) {
            ToastAllert.toatallert(context,"error");
        } finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                ToastAllert.toatallert(context,"error");
            }
        }
    }

    public static Setting getSetting(Context context)
    {

        FileInputStream fin = null;

        try {
            fin = context.openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            Setting setting = new Setting();
            setting = (Setting) ObjectType.getObject(text,setting);


            return  setting;

        }
        catch(IOException ex) {
          ToastAllert.toatallert(context,"Error get setting");
          return  new Setting();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

            }

        }

    }

    public static void setUserData(Context context){
        FileOutputStream fos = null;
        try {
            String text = ObjectType.getJson(StaticModels.userInfo);
            fos = context.openFileOutput(FILE_USER,MODE_PRIVATE);
            fos.write(text.getBytes());
            ToastAllert.toatallert(context,"your data is Save");
        }
        catch(IOException ex) {

            ToastAllert.toatallert(context,"error");
        } finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                ToastAllert.toatallert(context,"error");
            }
        }
    }

    public static UserInfo getUserData(Context context)
    {

        FileInputStream fin = null;

        try {
            fin = context.openFileInput(FILE_USER);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            UserInfo userInfo = new UserInfo();
            userInfo = (UserInfo) ObjectType.getObject(text,userInfo);
            return  userInfo;

        }
        catch(IOException ex) {
            ToastAllert.toatallert(context,"Error get your data");
            return  null;
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

            }

        }

    }


    public static void setInterlocutorData(Context context){
        FileOutputStream fos = null;
        try {
            String text = ObjectType.getJson(StaticModels.interlocutorInfo);
            fos = context.openFileOutput(FILE_INTERLOCUTOR,MODE_PRIVATE);
            fos.write(text.getBytes());
            ToastAllert.toatallert(context,"your partner data is Save");
        }
        catch(IOException ex) {

            ToastAllert.toatallert(context,"error");
        } finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                ToastAllert.toatallert(context,"error");
            }
        }
    }

    public static InterlocutorInfo getInterlocutorData(Context context)
    {

        FileInputStream fin = null;

        try {
            fin = context.openFileInput(FILE_INTERLOCUTOR);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            InterlocutorInfo interlocutorInfo = new InterlocutorInfo();
            interlocutorInfo = (InterlocutorInfo) ObjectType.getObject(text,interlocutorInfo);
            return  interlocutorInfo;

        }
        catch(IOException ex) {
            ToastAllert.toatallert(context,"Error get interlocutor data");
            return  null;
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

            }

        }

    }
}
