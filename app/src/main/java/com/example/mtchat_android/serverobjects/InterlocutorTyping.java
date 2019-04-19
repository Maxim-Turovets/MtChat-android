package com.example.mtchat_android.serverobjects;

public class InterlocutorTyping {
    private String objectType = "InterlocutorTyping";
    boolean typing;
    private String name;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }
}
