package com.example.mtchat_android.models;

public class ImageMessage {

    private  byte[] byteArray;
    private  boolean fromMe;

    public boolean isFromMe() {
        return fromMe;
    }

    public void setFromMe(boolean fromMe) {
        this.fromMe = fromMe;
    }

    public ImageMessage(byte[] byteArray, boolean fromMe) {
        this.byteArray = byteArray;
        this.fromMe = fromMe;
    }

    public ImageMessage(byte[] byteArray) {
        this.byteArray = byteArray;
    }
    public ImageMessage(){}

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }
}
