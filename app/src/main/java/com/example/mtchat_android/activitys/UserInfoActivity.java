package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mtchat_android.R;

import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.serverobjects.UserInfo;

public class UserInfoActivity extends AppCompatActivity {


    private ImageButton femaleBtn;
    private ImageButton maleBtn;
    private ImageButton anonBtn;
    private EditText inpName;
    private EditText inpAge;
    private boolean genderChoose = false;
    private MotionLayout userInfoLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);

        // Initialization
        femaleBtn = findViewById(R.id.femaleBtn);
        maleBtn = findViewById(R.id.maleBtn);
        //anonBtn = findViewById(R.id.anonBtn);
        inpName = findViewById(R.id.inpName);
        inpAge = findViewById(R.id.inpAge);
        userInfoLayout = findViewById(R.id.user_info_container);
        StaticModels.userInfo = new UserInfo();
        StaticModels.userInfo.setObjectType("UserInfo");


        if(StaticModels.setting.isRememberMyData())
        {
            StaticModels.userInfo = SettingInfo.getUserData(this);
            inpName.setText(StaticModels.userInfo.getName());
            inpAge.setText(StaticModels.userInfo.getAge());
            genderChoose = true;
            editColorgender(StaticModels.userInfo.getGender());

        }
    }


    public void femaleButtonPress(View view) {
        // active
        editColorgender("female");
        inpName.setEnabled(true);
        inpAge.setEnabled(true);
        inpName.setHint("Enter your name");
        inpAge.setHint("Enter your age");
        StaticModels.isAnonimGender = false;
        // not active
        maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//        anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //gender
        StaticModels.userInfo.setGender("female");
        genderChoose = true;
    }

    public void maleButtonPress(View view) {
        // active
        editColorgender("male");
        inpName.setEnabled(true);
        inpAge.setEnabled(true);
        inpName.setHint("Enter your name");
        inpAge.setHint("Enter your age");
        StaticModels.isAnonimGender = false;
        // not active
        femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
    //    anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //gender
        StaticModels.userInfo.setGender("male");
        genderChoose = true;



    }

//    public void anonButtonPress(View view) {
//        editColorgender("anonim");
//        // input field
//        inpName.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
//        inpAge.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
//        inpName.setEnabled(false);
//        inpAge.setEnabled(false);
//        StaticModels.isAnonimGender = true;
//        inpName.setHint("Anonim");
//        inpAge.setHint("Anonim");
//
//        // genderChoose = true;
//    }

    public void userInfoNextBtnPress(View view) {
        if(StaticModels.isAnonimGender)
        {
            StaticModels.userInfo.setName("Anonim");
            StaticModels.userInfo.setAge("20");
            goToNextLayout();
        }
        else {
            String localName = inpName.getText().toString().trim();
            String localAge = inpAge.getText().toString().trim();
            // text edit not empty
            if (!localName.equals("") && !localAge.trim().equals("") && genderChoose) {
                int localAgeInteger = Integer.parseInt(localAge);
                if (localAgeInteger > 100 || localAgeInteger < 1) {
                    userInfoLayout.transitionToStart();
                    userInfoLayout.transitionToEnd();
                    Toast toast = Toast.makeText(this, "Возвраст может быть от 1 то 100", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // info normal
                    StaticModels.userInfo.setName(inpName.getText().toString());
                    StaticModels.userInfo.setAge(inpAge.getText().toString());
                    goToNextLayout();
                }
            } else {
                userInfoLayout.transitionToStart();
                userInfoLayout.transitionToEnd();
                Toast toast = Toast.makeText(this, "Укажите все параметры ", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }

    private void  goToNextLayout()
    {
        if(StaticModels.connectInfo.getChatType().equals("general"))
        {
            this.finish();
            StartSocketConnection.startSocketConnection("chat3");
            StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.connectInfo));
            StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));
            StaticModels.interlocutorInfo = new InterlocutorInfo();
            StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");
            StaticModels.interlocutorInfo.setAgeTo("99");
            StaticModels.interlocutorInfo.setAgeFrom("1");
            StaticModels.interlocutorInfo.setGender("male");
            StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
        else {
            this.finish();
            Intent intent = new Intent(this, InterlocutorInfoActivity.class);
            startActivity(intent);
        }
    }


    private void   editColorgender(String gender)
    {
        if(gender.equals("male"))
        {
            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//            anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }
        if(gender.equals("female"))
        {
            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
       //     anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }
//        if (gender.equals("anonim"))
//        {
//            //active
//            anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
//            StaticModels.userInfo.setGender("anonim");
//            // not active
//            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//        }

    }
}


