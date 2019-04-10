package com.example.mtchat_android.serverobjects;

public class IfRoomCreated {

    private String objectType = "IfRoomCreated";
    private String nameInterlocutor;

    public IfRoomCreated(String nameInterlocutor) {
        this.nameInterlocutor = nameInterlocutor;
    }

    public String getNameInterlocutor() {
        return nameInterlocutor;
    }

    public void setNameInterlocutor(String nameInterlocutor) {
        this.nameInterlocutor = nameInterlocutor;
    }



}
