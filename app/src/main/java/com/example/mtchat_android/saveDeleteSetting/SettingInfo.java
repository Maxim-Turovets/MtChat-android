package com.example.mtchat_android.saveDeleteSetting;

import android.content.Context;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.Setting;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.toasts.ToastAllert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import static android.content.Context.MODE_PRIVATE;

public class SettingInfo {

    private final static String FILE_NAME = "setting.txt";

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
