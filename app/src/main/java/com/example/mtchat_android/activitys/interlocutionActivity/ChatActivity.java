package com.example.mtchat_android.activitys.interlocutionActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mtchat_android.activitys.EchoWebSocketListener;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.ImageMessageAdapter;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.Message;


import com.example.mtchat_android.models.MessageAdapter;
import com.example.mtchat_android.R;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends AppCompatActivity {


    private EditText editText;
    private MessageAdapter messageAdapter;
    private ImageMessageAdapter imageMessageAdapter;
    private ListView messagesView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        editText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        imageMessageAdapter = new ImageMessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
//        messagesView.setAdapter(messageAdapter);
        messagesView.setAdapter(imageMessageAdapter);
        EchoWebSocketListener.chatActivity=this;

    }



    public void sendMessage(View view) {

        Message myMessage = new Message();
        myMessage.setName(StaticModels.userInfo.getName());
        myMessage.setObjectType("Message");
        myMessage.setText(editText.getText().toString());
        myMessage.setTime("00:00");


        final String message = editText.getText().toString();
        if (message.length() > 0) {
            onMessage(myMessage);
            StartSocketConnection.webSocket.send(ObjectType.getJson(myMessage));
        }
    }

    public  void  onMessage (final Message message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
                if(message.getName().equals(StaticModels.userInfo.getName()))
                editText.setText("");
            }
        });

    }

    public  void  onImageMessage (final ImageMessage message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageMessageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });

    }



}