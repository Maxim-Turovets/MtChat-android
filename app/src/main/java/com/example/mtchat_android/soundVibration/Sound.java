package com.example.mtchat_android.soundVibration;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.mtchat_android.R;

public class Sound {

    public static void soundPlay(Context context) {
        MediaPlayer  messageSound = MediaPlayer.create(context, R.raw.message);
        messageSound.start();
    }

}
