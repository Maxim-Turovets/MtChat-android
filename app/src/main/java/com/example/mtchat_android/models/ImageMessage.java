
package com.example.mtchat_android.models;

public class ImageMessage {

    private  boolean fromMe;
    private StringBuffer image;

    public StringBuffer getImage() {
        return image;
    }

    public void setImage(StringBuffer image) {
        this.image = image;
    }

    public boolean isFromMe() {
        return fromMe;
    }

    public void setFromMe(boolean fromMe) {
        this.fromMe = fromMe;
    }
}
