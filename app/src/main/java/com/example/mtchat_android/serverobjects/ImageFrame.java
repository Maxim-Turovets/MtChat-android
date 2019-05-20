package com.example.mtchat_android.serverobjects;

public class ImageFrame {
      private String objectType ="ImageFrame";
    private int numberFrame;
    String frame;

    public String getObjectType() {
        return objectType;
    }


    public int getNumberFrame() {
        return numberFrame;
    }

    public void setNumberFrame(int numberFrame) {
        this.numberFrame = numberFrame;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }


    public ImageFrame(int numberFrame, String frame) {
        this.numberFrame = numberFrame;
        this.frame = frame;
    }

    public ImageFrame() {

    }
}
