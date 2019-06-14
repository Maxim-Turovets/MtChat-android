package com.example.mtchat_android.serverobjects;

public class UserGeneralInfo {
    private String objectType = "UserGeneralInfo";
    private String  name;

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
}
