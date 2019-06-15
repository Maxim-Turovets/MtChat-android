package com.example.mtchat_android.soundVibration;

import android.content.Context;
import android.os.Vibrator;

public class Vibrate {

//    public Vibrate(Context context) {
//        long[] pattern = {100, 300, 400, 300};
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        if (vibrator.hasVibrator()) {
//            vibrator.vibrate(pattern, -1);
//        }
//    }

    public static void vibration(Context context) {
        long[] pattern = {100, 300, 400, 300};
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(pattern, -1);
        }
    }
}
