package com.example.mtchat_android.models;

public class Setting {
    private boolean goToChat;
    private boolean sound;
    private boolean vibration;
    private boolean rememberMyData;
    private boolean rememberInterlocutor;

    public boolean isGoToChat() {
        return goToChat;
    }

    public void setGoToChat(boolean goToChat) {
        this.goToChat = goToChat;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isVibration() {
        return vibration;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public boolean isRememberMyData() {
        return rememberMyData;
    }

    public void setRememberMyData(boolean rememberMyData) {
        this.rememberMyData = rememberMyData;
    }

    public boolean isRememberInterlocutor() {
        return rememberInterlocutor;
    }

    public void setRememberInterlocutor(boolean rememberInterlocutor) {
        this.rememberInterlocutor = rememberInterlocutor;
    }
}
