package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mtchat_android.R;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.saveDeleteSetting.SettingInfo;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;
import com.example.mtchat_android.toasts.ToastAlert;

public class InterlocutorInfoActivity  extends AppCompatActivity {

    private ImageButton interlocutorFemaleBtn;
    private ImageButton interlocutorMaleBtn;
    private ImageButton interlocutorAnonBtn;
    private EditText inpFrom;
    private EditText inpTo;
    private boolean genderChoose = false;
    private MotionLayout interlocutorInfoLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interlocutor_info_layout);

        //init
        interlocutorFemaleBtn = findViewById(R.id.interlocutorFemaleBtn);
        interlocutorMaleBtn = findViewById(R.id.interlocutorMaleBtn);
        //interlocutorAnonBtn = findViewById(R.id.interlocutorAnonBtn);
        inpFrom = findViewById(R.id.inpFrom);
        inpTo = findViewById(R.id.inpTo);
        interlocutorInfoLayout = findViewById(R.id.interlocutor_info_container);
        StaticModels.interlocutorInfo = new InterlocutorInfo();
        StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");




        if(StaticModels.setting.isGoToChat()) {
                StaticModels.interlocutorInfo = SettingInfo.getInterlocutorData(this);
                genderChoose= true;
                sendInfoToServer();
                goToNextLayout();
        }
        else if (StaticModels.setting.isRememberInterlocutor()) {
            StaticModels.interlocutorInfo = SettingInfo.getInterlocutorData(this);
            inpTo.setText(StaticModels.interlocutorInfo.getAgeTo());
            inpFrom.setText(StaticModels.interlocutorInfo.getAgeFrom());
            genderChoose = true;
            editColorgender(StaticModels.interlocutorInfo.getGender());
        }

        isAnonimGender();
    }


    public  void  interlocutorFemaleButtonPress(View view)
    {
        if(StaticModels.isAnonimGender==false) {
            // active
            inputTextFieldUnlock(true);
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
       //     interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            //gender
            StaticModels.interlocutorInfo.setGender("female");
            genderChoose = true;
        }
        //
    }

    public  void  interlocutorMaleButtonPress(View view)
    {
        if(StaticModels.isAnonimGender==false)  // if not anonim gender
        {
            // active
            inputTextFieldUnlock(true);
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
         //   interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            genderChoose = true;
            //gender
            StaticModels.interlocutorInfo.setGender("male");
        }
    }

//    public  void  interlocutorAnonButtonPress(View view)
//    {
//        if(StaticModels.isAnonimGender==false) {
//
//            //active
//            interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
//            // not active
//            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
//            genderChoose = true;
//            //gender
//            StaticModels.interlocutorInfo.setGender("anonim");
//            StaticModels.interlocutorInfo.setAgeFrom("0");
//            StaticModels.interlocutorInfo.setAgeTo("99");
//            inputTextFieldUnlock(false);
//        }
//    }

    private  boolean isAnonimGender()
    {
        if(StaticModels.isAnonimGender)
        {
            // active
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        //    interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            //gender
            StaticModels.interlocutorInfo.setGender("anonim");
            StaticModels.interlocutorInfo.setAgeTo("99");
            StaticModels.interlocutorInfo.setAgeFrom("0");
            inputTextFieldUnlock(false); // lock text field
            return  false;
        }
        else {
            return true;
        }
    }
    public void searchBtnPress(View view)
    {
        if(StaticModels.isAnonimGender)
        {
            sendInfoToServer();
            goToNextLayout();
        }
        else {
            String localFrom = inpFrom.getText().toString().trim();
            String localTo = inpTo.getText().toString().trim();
            // text edit not empty
            if (!localFrom.equals("") && !localTo.trim().equals("") && genderChoose) {
                int localFromInteger = Integer.parseInt(localFrom);
                int localToInteger = Integer.parseInt(localTo);
                if (localFromInteger > 100 || localFromInteger < 1 || localToInteger > 100 || localToInteger < 1 || localFromInteger >= localToInteger) {
                    interlocutorInfoLayout.transitionToStart();
                    interlocutorInfoLayout.transitionToEnd();
                    ToastAlert.toastAlert(this,"Возвраст может быть от 1 то 100");
                } else {
                    // info normal

                    StaticModels.interlocutorInfo.setAgeFrom(localFrom);
                    StaticModels.interlocutorInfo.setAgeTo(localTo);
                    sendInfoToServer();
                    goToNextLayout();
                }
            } else {
                interlocutorInfoLayout.transitionToStart();
                interlocutorInfoLayout.transitionToEnd();
                ToastAlert.toastAlert(this,"Укажите все параметры ");
            }
        }

    }




    @Override
    public void onBackPressed() {

            this.finish();
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);

    }


    private void  goToNextLayout()
    {
        this.finish();
        Intent intent = new Intent(this, LoadingAnimationActivity.class);
        startActivity(intent);
    }

    private  void  sendInfoToServer(){
        StartSocketConnection.startSocketConnection("chat");
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.connectInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));
        StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
    }

    private void  inputTextFieldUnlock(boolean status){
        if(!status) {
            inpFrom.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            inpTo.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        }
        else {
            inpFrom.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            inpTo.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }
        inpFrom.setEnabled(status);
        inpTo.setEnabled(status);

    }


    private void   editColorgender(String gender)
    {
        if(gender.equals("male"))
        {
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
          //  interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }
        if(gender.equals("female"))
        {
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
          //  interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }
        if (gender.equals("anonim"))
        {
            //active
         //   interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        }

    }



}
