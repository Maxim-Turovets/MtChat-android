package com.example.mtchat_android.models;

public class ImageMessage {

    private  byte[] byteArray;

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
