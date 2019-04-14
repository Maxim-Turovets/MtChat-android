package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mtchat_android.R;
import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;

public class SetPartnerGenderActivity extends AppCompatActivity {
    Button genderMale;
    Button genderFemale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_partner_gender);

        genderMale = (Button)findViewById(R.id.btnSetPartnerGenderMale);
        genderFemale = (Button)findViewById(R.id.btnSetPartnerGenderFemale);

    }


    public  void goToSetPartnerAgeMale(View view)
    {
        StaticModels.interlocutorInfo = new InterlocutorInfo();
        StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");
        StaticModels.interlocutorInfo.setGender("male");
        Intent intent = new Intent(this, SetPartnerAgeActivity.class);
        startActivity(intent);
    }

    public  void goToSetPartnerAgeFemale(View view)
    {
        StaticModels.interlocutorInfo = new InterlocutorInfo();
        StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");
        StaticModels.interlocutorInfo.setGender("female");
        Intent intent = new Intent(this, SetPartnerAgeActivity.class);
        startActivity(intent);
    }
}
