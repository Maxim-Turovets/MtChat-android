package com.example.mtchat_android.serverobjects;

public class ImageCanSend {
    private String objectType = "ImageCanSend";
    private boolean  available;

    public String getObjectType() {
        return objectType;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
