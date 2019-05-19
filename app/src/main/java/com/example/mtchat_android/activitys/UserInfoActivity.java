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

import com.example.mtchat_android.models.StaticModels;
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
        anonBtn = findViewById(R.id.anonBtn);
        inpName = findViewById(R.id.inpName);
        inpAge = findViewById(R.id.inpAge);
        userInfoLayout = findViewById(R.id.user_info_container);
        StaticModels.userInfo = new UserInfo();
        StaticModels.userInfo.setObjectType("UserInfo");
    }


    public void femaleButtonPress(View view) {
        // active
        femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //gender
        StaticModels.userInfo.setGender("female");
        genderChoose = true;
    }

    public void maleButtonPress(View view) {
        // active
        maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //gender
        StaticModels.userInfo.setGender("male");
        genderChoose = true;
    }

    public void anonButtonPress(View view) {
        //active
        anonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        femaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        maleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        // genderChoose = true;
    }

    public void userInfoNextBtnPress(View view) {
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
                this.finish();
                Intent intent = new Intent(this, InterlocutorInfoActivity.class);
                startActivity(intent);
            }
        } else {
            userInfoLayout.transitionToStart();
            userInfoLayout.transitionToEnd();
            Toast toast = Toast.makeText(this, "Укажите все параметры ", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }


}


