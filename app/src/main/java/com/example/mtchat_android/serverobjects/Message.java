package com.example.mtchat_android.serverobjects;



public class Message {

    private  String objectType;
    private  String name;
    private  String text;
//    private  String time;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

}
