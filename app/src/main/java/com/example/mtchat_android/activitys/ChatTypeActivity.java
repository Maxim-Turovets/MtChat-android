package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.models.StartSocketConnection;

public class ChatTypeActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_type_layout);

    }


    public  void goToChat(View view)
    {
        StartSocketConnection.startSocketConnection();
        StartSocketConnection.sendTestJson();

        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
