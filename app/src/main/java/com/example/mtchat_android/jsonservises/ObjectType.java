package com.example.mtchat_android.jsonservises;

import com.google.gson.Gson;


public class ObjectType{

    private static Gson gson = new Gson();


    public static Object getObject(String stringObject,Object object){

        return  gson.fromJson(stringObject, object.getClass());
    }

    public static String getJson(Object object){

        return gson.toJson(object);
    }


}

