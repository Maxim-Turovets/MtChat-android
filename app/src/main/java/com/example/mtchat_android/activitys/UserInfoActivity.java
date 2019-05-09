package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;

import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.ImageMessage;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.UserInfo;

public class UserInfoActivity  extends  AppCompatActivity{


        private ImageButton femaleBtn;
        private ImageButton maleBtn;
        private ImageButton anonBtn;
        private EditText inpName;
        private EditText inpAge;
        private boolean genderChoose = false;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.this_user_info_layout);

            // Initialization
            femaleBtn = findViewById(R.id.femaleBtn);
            maleBtn = findViewById(R.id.maleBtn);
            anonBtn = findViewById(R.id.anonBtn);
            inpName = findViewById(R.id.inpName);
            inpAge = findViewById(R.id.inpAge);
            StaticModels.userInfo = new UserInfo();
            StaticModels.userInfo.setObjectType("UserInfo");
        }



        public  void  femaleButtonPress(View view)
        {
            // active
            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            //gender
            StaticModels.userInfo.setGender("female");
            genderChoose = true;
        }

        public  void  maleButtonPress(View view)
        {
            // active
            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            genderChoose = true;
        }

        public  void  anonButtonPress(View view)
        {
            //active
            anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
            // not active
            femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
            genderChoose = true;
        }

        public  void  userInfoNextBtnPress(View view)
        {
            String localName = inpName.getText().toString().trim();
            String localAge = inpAge.getText().toString().trim();
            // text edit not empty
            if(!localName.equals("") && !localAge.trim().equals("") && genderChoose)
                 {
                     int localAgeInteger = Integer.parseInt(localAge);
                     if(localAgeInteger>100||localAgeInteger<0)
                     {
                         Toast toast = Toast.makeText(this, "age must be more 0 and less 100", Toast.LENGTH_SHORT);
                         toast.show();
                     }
                     else {
                         // info normal
                         StaticModels.userInfo.setName(inpName.getText().toString());
                         StaticModels.userInfo.setAge(inpAge.getText().toString());
                         StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.userInfo));
                         Intent intent = new Intent(this, InterlocutorInfoActivity.class);
                         startActivity(intent);
                     }
                }
            else {
                Toast toast = Toast.makeText(this, "Fill in all the fields ", Toast.LENGTH_SHORT);
                toast.show();
            }

        }


    }


