package com.example.mtchat_android.models;

import com.example.mtchat_android.serverobjects.Message;

public class MergedMessage {
    private Message textMessage;
    private ImageMessage imageMessage;

    public MergedMessage(Message textMessage) {
        this.textMessage = textMessage;
    }

    public MergedMessage(ImageMessage imageMessage) {
        this.imageMessage = imageMessage;
    }

    public MergedMessage(Message textMessage, ImageMessage imageMessage) {
        this.textMessage = textMessage;
        this.imageMessage = imageMessage;
    }

    public Message getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(Message textMessage) {
        this.textMessage = textMessage;
    }

    public ImageMessage getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(ImageMessage imageMessage) {
        this.imageMessage = imageMessage;
    }
}
