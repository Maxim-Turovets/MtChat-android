package com.example.mtchat_android.activitys.interlocutionActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


public class ChatActivity extends AppCompatActivity {


    private EditText editText;
    private MessageAdapter messageAdapter;
    private ImageMessageAdapter imageMessageAdapter;
    private ListView messagesView;

    private  static  final  int PICK_IMAGE =100;
    private Uri imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        editText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        imageMessageAdapter = new ImageMessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
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


    public   void openGallery(View view){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView = new ImageView(this);

        if(resultCode==RESULT_OK&& requestCode==PICK_IMAGE)
            imageUrl = data.getData();

        imageView.setImageURI(imageUrl);

        ViewGroup layout = (ViewGroup) findViewById(android.R.id.content);
       // layout.addView(imageView);
//        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
//        Bitmap bitmap = drawable.getBitmap();

    }
}

