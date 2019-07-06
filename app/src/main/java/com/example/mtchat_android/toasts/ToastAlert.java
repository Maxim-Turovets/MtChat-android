package com.example.mtchat_android.toasts;

import android.content.Context;
import android.widget.Toast;

public    class ToastAlert {

    public  static  void toastAlert(Context context, String message)
    {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
