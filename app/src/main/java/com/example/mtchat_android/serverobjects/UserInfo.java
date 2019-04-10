package com.example.mtchat_android.serverobjects;

public class UserInfo {
    private String objectType;
    private String  name;
    private String  gender;
    private String  age;
    private boolean voiceMessage;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isVoiceMessage() {
        return voiceMessage;
    }

    public void setVoiceMessage(boolean voiceMessage) {
        this.voiceMessage = voiceMessage;
    }
}
