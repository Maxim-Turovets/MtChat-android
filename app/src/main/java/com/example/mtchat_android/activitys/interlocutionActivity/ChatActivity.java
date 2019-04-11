package com.example.mtchat_android.activitys.interlocutionActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.serverobjects.Message;


import com.example.mtchat_android.models.*;
//import com.example.mtchat_android.models.Message;
import com.example.mtchat_android.models.MessageAdapter;
import com.example.mtchat_android.R;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends AppCompatActivity {


    public static ArrayList<Message> mList;

    private EditText editText;
    public static MessageAdapter messageAdapter;
    private ListView messagesView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        editText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        mList = new ArrayList<>();


    }



    public void sendMessage(View view) {

//        Message myMessage = new Message();
//        myMessage.setName("Android");
//        myMessage.setObjectType("Message");
//        myMessage.setText(editText.getText().toString()+"  "+ChatActivity.mList.size()+"  "+ResponseServer.getResponseServerString());
//        myMessage.setTime("00:00");
//
//        mList.add(myMessage);

//         final String message = editText.getText().toString();
//        if (message.length() > 0) {
//          //  MemberData data = new MemberData("Max","FF963A3A");
//            boolean belongsToCurrentUser = false;
//          //  final Message ms = new Message(message, data, belongsToCurrentUser);
//                    messageAdapter.add(myMessage);
//                    messagesView.setSelection(messagesView.getCount() - 1);
//                    editText.setText("");
//                    ResponseServer.webSocket.send(ObjectType.getJson(myMessage));


        }
    }







//}





