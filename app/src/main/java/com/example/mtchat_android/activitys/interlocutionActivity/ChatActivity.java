package com.example.mtchat_android.activitys.interlocutionActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.ChatCloseActivity;
import com.example.mtchat_android.activitys.ChatTypeActivity;
import com.example.mtchat_android.activitys.EchoWebSocketListener;
import com.example.mtchat_android.activitys.InterlocutorInfoActivity;
import com.example.mtchat_android.activitys.LoadingAnimationActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.AdapterMessage;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.MergedMessage;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.models.TypeWriter;
import com.example.mtchat_android.serverobjects.ImageCanSend;
import com.example.mtchat_android.serverobjects.ImageFrame;
import com.example.mtchat_android.serverobjects.InterlocutorTyping;
import com.example.mtchat_android.serverobjects.Message;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;


import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import okio.ByteString;


public class ChatActivity extends AppCompatActivity {

    private FlowingDrawer mDrawer;
    private EmojiconEditText editText;
    private AdapterMessage adapterMessage;
    private ListView messagesView;
    private Switch imageMessageSwitch;
    private ImageButton imageMessageButton;
    private ImageButton textMessageButton;
    private boolean showButton;
    private MediaPlayer messageSound;
    private ImageButton reconnectBtn;
    private ImageButton goToMenuBtn;
    private ImageButton openCameraBtn;
    private int countClickedBackButton = 0;

    ImageButton emojiImageButton;
    View rootView;
    EmojIconActions emojIcon;

    private static final int PICK_IMAGE = 100;
    private Uri imageUrl;
    int maxim = 0;

    TypeWriter tw;

    @Override
    public void onBackPressed() {
        countClickedBackButton++;
        if(countClickedBackButton==2)
        {
            this.finish();
            StartSocketConnection.webSocket.close(4999, "Recconect");
            Intent intent = new Intent(this, InterlocutorInfoActivity.class);
            startActivity(intent);
        }
        Toast toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT);
        toast.show();

        new CountDownTimer(2_000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                countClickedBackButton=0;
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        adapterMessage = new AdapterMessage(this);

        editText = (EmojiconEditText) findViewById(R.id.editText);
        messagesView = (ListView) findViewById(R.id.messages_view);
        imageMessageSwitch = (Switch) findViewById(R.id.imageMessageSwitch);
        imageMessageButton = (ImageButton) findViewById(R.id.btnSendImage);
        textMessageButton = (ImageButton) findViewById(R.id.btnSendMessage);
        reconnectBtn = findViewById(R.id.recconectBtn);
        goToMenuBtn = findViewById(R.id.goToMenuBtn);
        openCameraBtn = findViewById(R.id.openCameraBtn);
        imageMessageButton.setVisibility(View.GONE);

        /// Sound message
        messageSound = MediaPlayer.create(this, R.raw.message);


        /// Smile
        rootView = findViewById(R.id.root_view);
        emojiImageButton = (ImageButton) findViewById(R.id.emoji_btn);
        emojIcon = new EmojIconActions(this, rootView, editText, emojiImageButton);
        emojIcon.ShowEmojIcon();
        emojIcon.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.smiley);
        /// end smile

        messagesView.setAdapter(adapterMessage);
        EchoWebSocketListener.chatActivity = this;

        tw = (TypeWriter) findViewById(R.id.tv);
        tw.setVisibility(View.GONE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = editText.getText().length();

                if (size > 0)
                    if (maxim == 0) {
                        InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                        interlocutorTyping.setTyping(true);
                        interlocutorTyping.setName(StaticModels.userInfo.getName());
                        StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                        imageMessageButton.setVisibility(View.GONE);
                        textMessageButton.setVisibility(View.VISIBLE);
                        maxim++;
                    }
                if (size == 0) {
                    maxim = 0;
                    InterlocutorTyping interlocutorTyping = new InterlocutorTyping();
                    interlocutorTyping.setTyping(false);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(interlocutorTyping));
                    if (showButton) {
                        imageMessageButton.setVisibility(View.VISIBLE);
                        textMessageButton.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.print("dsgfh");

            }
        });


        /////
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {

                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {

            }
        });

        //////

        imageMessageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(false);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                }
                if (isChecked == false) {
                    ImageCanSend imageCanSend = new ImageCanSend();
                    imageCanSend.setAvailable(true);
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageCanSend));
                }
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
        countClickedBackButton = 0;
    }

    public void onMessage(final MergedMessage message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterMessage.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
                if (message.getTextMessage() != null)
                    if (message.getTextMessage().getName().equals(StaticModels.userInfo.getName()))
                        editText.setText("");
            }
        });
    }

    public void openGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView = new ImageView(this);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUrl = data.getData();

            imageView.setImageURI(imageUrl);

            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 15 , baos);
            byte[] imageInByte = baos.toByteArray();
            byte[] one_bit = new byte[1];


            ByteString byteString = ByteString.of(imageInByte);
            ByteString byteString2 = ByteString.of(one_bit);

            String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
            StringBuffer str = new StringBuffer();
            StringBuffer mystr = new StringBuffer();

            int strSixe = encodedImage.length();

            int indexFrame=0;
            ImageFrame imageFrame = new ImageFrame();

            for(int i =0;i<encodedImage.length();i++)
            {
                if(i==encodedImage.length()-1) {
                    str.append(encodedImage.charAt(i));
                    imageFrame.setFrame(str.toString());
                    imageFrame.setNumberFrame(-1);
                    mystr.append(encodedImage.charAt(i));
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageFrame));
                }
                if(str.length()<5_000)
                {
                    str.append(encodedImage.charAt(i));
                    mystr.append(encodedImage.charAt(i));
                }
                else{
                    imageFrame.setNumberFrame(indexFrame);
                    str.append(encodedImage.charAt(i));
                    mystr.append(encodedImage.charAt(i));
                    imageFrame.setFrame(str.toString());
                    StartSocketConnection.webSocket.send(ObjectType.getJson(imageFrame));
                    indexFrame++;
                    str= new StringBuffer();
                }
            }



            //        ByteString f = ByteString.encodeUtf8(encodedImage);
            //       StartSocketConnection.webSocket.send(f);



//            String encoded = Base64.encodeToString(imageInByte, Base64.DEFAULT);
//            int a = encoded.length();

            // StartSocketConnection.webSocket.send();
//            StartSocketConnection.webSocket.send(byteString);
//            StartSocketConnection.webSocket.send(byteString2);

            ImageMessage myImageMessage = new ImageMessage();
            myImageMessage.setFromMe(true);
            myImageMessage.setImage(mystr);

            MergedMessage mergedMessage = new MergedMessage(myImageMessage);
            onMessage(mergedMessage);
        } else {
            Toast toast = Toast.makeText(this, "Select please a photo", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void goToChatClose() {
        Intent intent = new Intent(this, ChatCloseActivity.class);
        startActivity(intent);
    }

    public void showPersonTyping() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tw.setVisibility(View.VISIBLE);
                tw.setText("");
                tw.setCharacterDelay(150);
                tw.animateText(StaticModels.interlocutorName + "  typing ✍ ... ");
            }
        });
    }


    public void hidePersonTyping() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tw.setVisibility(View.GONE);
            }
        });
    }

    public void hideButtonImageSend() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageMessageButton.setVisibility(View.GONE);
                textMessageButton.setVisibility(View.VISIBLE);
                showButton = false;
            }
        });

    }

    public void showButtonImageSend() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageMessageButton.setVisibility(View.VISIBLE);
                textMessageButton.setVisibility(View.GONE);
                showButton = true;
            }
        });

    }


    public void soundPlay() {
        messageSound.start();
    }

    public void reconnectBtnPress(View view) {
        reconnectBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        StartSocketConnection.webSocket.close(4999, "Recconect");


        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);

        StartSocketConnection.startSocketConnection();
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.connectInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
    }

    public void goToMenuBtnPress(View view) {
        goToMenuBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        StartSocketConnection.webSocket.close(4999, "Recconect");

        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }

    public void openCameraBtnPress(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);

        openCameraBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));

    }

}