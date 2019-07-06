package com.example.mtchat_android.activitys.interlocutionActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
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
import com.example.mtchat_android.activitys.settingActivity.SettingActivity;
import com.example.mtchat_android.activitys.interlocutionActivity.flowing.Flowing;
import com.example.mtchat_android.activitys.interlocutionActivity.flowing.SwitchServise;
import com.example.mtchat_android.activitys.interlocutionActivity.keyboards.KeyboardServise;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.AdapterMessage;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.MergedMessage;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.models.TypeWriter;
import com.example.mtchat_android.serverobjects.ImageFrame;
import com.example.mtchat_android.serverobjects.Message;
import com.example.mtchat_android.toasts.ToastAlert;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import java.io.ByteArrayOutputStream;
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
    private ImageButton settingButton;
    private boolean showButton;
    private View rootViewEmoji;
    private ImageButton reconnectBtn;
    private ImageButton goToMenuBtn;
    private ImageButton openCameraBtn;
    private int countClickedBackButton = 0;

    ImageButton emojiImageButton;

    private  EmojIconActions emojIcon;

    private static final int PICK_IMAGE = 100;
    private Uri imageUrl;


    TypeWriter isTypingWriter;

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
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        rootViewEmoji = findViewById(R.id.root_view);
        emojiImageButton = (ImageButton) findViewById(R.id.emoji_btn);
        isTypingWriter = (TypeWriter) findViewById(R.id.isTyping);
        settingButton = (ImageButton) findViewById(R.id.settingBtn);
        imageMessageButton.setVisibility(View.GONE);

        // Smile
        emojIcon = new EmojIconActions(this, rootViewEmoji, editText, emojiImageButton);
        emojIcon.ShowEmojIcon();
        emojIcon.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.smiley);
        /// end smile

        messagesView.setAdapter(adapterMessage);
        EchoWebSocketListener.chatActivity = this;

        isTypingWriter.setVisibility(View.GONE);

        // keyboards
        new KeyboardServise(editText, imageMessageButton, textMessageButton , showButton);
        // левая шторка
        new Flowing(mDrawer);
        // switch
        new SwitchServise(imageMessageSwitch);

    }


    public void sendMessage(View view) {


        Message myMessage = new Message();
        myMessage.setName(StaticModels.userInfo.getName());
        myMessage.setObjectType("Message");
        myMessage.setText(editText.getText().toString());
      //  myMessage.setTime("00:00");
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

         try {
             ImageView imageView = new ImageView(this);

             if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                 imageUrl = data.getData();

                 imageView.setImageURI(imageUrl);

                 Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                 bitmap.compress(Bitmap.CompressFormat.JPEG, 15, baos);
                 byte[] imageInByte = baos.toByteArray();


                 ByteString byteString = ByteString.of(imageInByte);

                 String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                 StringBuffer str = new StringBuffer();
                 StringBuffer mystr = new StringBuffer();

                 int strSixe = encodedImage.length();

                 int indexFrame = 0;
                 ImageFrame imageFrame = new ImageFrame();

                 if (strSixe < 5_000) {
                     for (int i = 0; i < encodedImage.length(); i++) {
                         str.append(encodedImage.charAt(i));
                     }
                     imageFrame.setNumberFrame(-2);
                     imageFrame.setFrame(str.toString());
                     StartSocketConnection.webSocket.send(ObjectType.getJson(imageFrame));
                 } else {

                     for (int i = 0; i < encodedImage.length(); i++) {

                         if (i == encodedImage.length() - 1) {
                             str.append(encodedImage.charAt(i));
                             imageFrame.setFrame(str.toString());
                             imageFrame.setNumberFrame(-1);
                             mystr.append(encodedImage.charAt(i));
                             StartSocketConnection.webSocket.send(ObjectType.getJson(imageFrame));
                         }
                         if (str.length() < 5_000) {
                             str.append(encodedImage.charAt(i));
                             mystr.append(encodedImage.charAt(i));
                         } else {
                             imageFrame.setNumberFrame(indexFrame);
                             str.append(encodedImage.charAt(i));
                             mystr.append(encodedImage.charAt(i));
                             imageFrame.setFrame(str.toString());
                             StartSocketConnection.webSocket.send(ObjectType.getJson(imageFrame));
                             indexFrame++;
                             str = new StringBuffer();
                         }
                     }

                 }


                 ImageMessage myImageMessage = new ImageMessage();
                 myImageMessage.setFromMe(true);
                 myImageMessage.setImage(mystr);

                 MergedMessage mergedMessage = new MergedMessage(myImageMessage);
                 onMessage(mergedMessage);
             } else {
                 ToastAlert.toastAlert(this,"Select please a photo");
             }
         }
         catch (Exception e){
             ToastAlert.toastAlert(this,"Error format image");
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
                isTypingWriter.setVisibility(View.VISIBLE);
                isTypingWriter.setText("");
                isTypingWriter.setCharacterDelay(150);
                isTypingWriter.animateText(StaticModels.interlocutorName + "  typing ✍ ... ");
            }
        });
    }


    public void hidePersonTyping() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                isTypingWriter.setVisibility(View.GONE);
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




    public void reconnectBtnPress(View view) {
        reconnectBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        StartSocketConnection.webSocket.close(4999, "Recconect");


        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);

        StartSocketConnection.startSocketConnection("chat");
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

    public void goToSettingBtnPress(View view) {
        settingButton.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));

        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);

        settingButton.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
    }



}