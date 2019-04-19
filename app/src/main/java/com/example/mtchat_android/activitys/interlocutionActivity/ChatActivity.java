package com.example.mtchat_android.activitys.interlocutionActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.ChatCloseActivity;
import com.example.mtchat_android.activitys.EchoWebSocketListener;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.AdapterMessage;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.MergedMessage;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.models.TypeWriter;
import com.example.mtchat_android.serverobjects.InterlocutorTyping;
import com.example.mtchat_android.serverobjects.Message;

import java.io.ByteArrayOutputStream;


import okio.ByteString;


public class ChatActivity extends AppCompatActivity {


    private EditText editText;
    //private MessageAdapter messageAdapter;
  //  private ImageMessageAdapter imageMessageAdapter;
    private AdapterMessage adapterMessage;
    private ListView messagesView;

    private  static  final  int PICK_IMAGE =100;
    private Uri imageUrl;
    int maxim = 0;

    TypeWriter tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        adapterMessage = new AdapterMessage(this);

        editText = (EditText) findViewById(R.id.editText);
      //  messageAdapter = new MessageAdapter(this);
      //  imageMessageAdapter = new ImageMessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
      //  messagesView.setAdapter(messageAdapter);
     //   messagesView.setAdapter(imageMessageAdapter);
          messagesView.setAdapter(adapterMessage);
        EchoWebSocketListener.chatActivity=this;

        tw = (TypeWriter) findViewById(R.id.tv);
         tw.setVisibility(View.GONE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = editText.getText().length();

                if(size>0)
                    if(maxim==0)
                    {
                        InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                        interlocutorTyping.setTyping(true);
                        interlocutorTyping.setName(StaticModels.userInfo.getName());
                        StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                        maxim++;
                    }
                if(size==0)
                {
                    maxim=0;
                    InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                    interlocutorTyping.setTyping(false);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.print("dsgfh");

            }
        });


    }



    public void sendMessage(View view) {


        Message myMessage = new Message();
        myMessage.setName(StaticModels.userInfo.getName());
        myMessage.setObjectType("Message");
        myMessage.setText(editText.getText().toString());
        myMessage.setTime("00:00");
        MergedMessage mergedMessage = new MergedMessage(myMessage);


        final String message = editText.getText().toString();
        if (message.length() > 0) {
            onMessage(mergedMessage);
            StartSocketConnection.webSocket.send(ObjectType.getJson(myMessage));
        }
    }

        public  void  onMessage (final MergedMessage message) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterMessage.add(message);
                    messagesView.setSelection(messagesView.getCount() - 1);
                    if(message.getTextMessage()!=null)
                    if (message.getTextMessage().getName().equals(StaticModels.userInfo.getName()))
                        editText.setText("");
                }
            });
        }


//    public  void  onMessage (final Message message)
//    {
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                messageAdapter.add(message);
//                messagesView.setSelection(messagesView.getCount() - 1);
//                if(message.getName().equals(StaticModels.userInfo.getName()))
//                editText.setText("");
//            }
//        });
//
//    }
//
//    public  void  onImageMessage (final ImageMessage message)
//    {
//         messagesView.setAdapter(imageMessageAdapter);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                imageMessageAdapter.add(message);
//                messagesView.setSelection(messagesView.getCount() - 1);
//            }
//        });
//
//    }


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

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        byte[] one_bit = new byte[1];

        ByteString byteString = ByteString.of(imageInByte);
        ByteString byteString2 = ByteString.of(one_bit);

        StartSocketConnection.webSocket.send(byteString);
        StartSocketConnection.webSocket.send(byteString2);

        ImageMessage myImageMessage = new ImageMessage(imageInByte, true);
        //onImageMessage(myImageMessage);
        MergedMessage mergedMessage = new MergedMessage(myImageMessage);
        onMessage(mergedMessage);
       // layout.addView(imageView);
//        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
//        Bitmap bitmap = drawable.getBitmap();

    }

    public void  goToChatClose(){
        Intent intent = new Intent(this, ChatCloseActivity.class);
        startActivity(intent);
    }

    public void showPersonTyping()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tw.setVisibility(View.VISIBLE);
                tw.setText("");
                tw.setCharacterDelay(150);
                tw.animateText(StaticModels.interlocutorName+"  typing ✍ ... ");
            }
        });
    }


    public void hidePersonTyping(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tw.setVisibility(View.GONE);
            }
        });
    }



}

