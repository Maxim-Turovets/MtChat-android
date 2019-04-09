package com.example.mtchat_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class chatActivity extends AppCompatActivity {



    private String channelID = "CHANNEL_ID_FROM_YOUR_SCALEDRONE_DASHBOARD";
    private String roomName = "observable-room";
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        editText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

    }



    public void sendMessage(View view) {

         final String message = editText.getText().toString();
        if (message.length() > 0) {
            MemberData data = new MemberData("Max","FF963A3A");
            boolean belongsToCurrentUser = false;
            final Message ms = new Message(message, data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(ms);
                    messagesView.setSelection(messagesView.getCount() - 1);
                    editText.setText("");
                }
            });
        }
    }



}
