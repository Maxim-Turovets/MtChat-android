package com.example.mtchat_android.serverobjects;

public class InterlocutorTyping {
    private String chatType;
    boolean typing;
    private String name;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }
}
