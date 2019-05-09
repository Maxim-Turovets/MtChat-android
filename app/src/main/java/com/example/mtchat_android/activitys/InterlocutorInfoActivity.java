package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mtchat_android.R;
import com.example.mtchat_android.jsonservises.ObjectType;
import com.example.mtchat_android.models.StartSocketConnection;
import com.example.mtchat_android.models.StaticModels;
import com.example.mtchat_android.serverobjects.InterlocutorInfo;

public class InterlocutorInfoActivity  extends AppCompatActivity {

    private ImageButton interlocutorFemaleBtn;
    private ImageButton interlocutorMaleBtn;
    private ImageButton interlocutorAnonBtn;
    private EditText inpFrom;
    private EditText inpTo;
    private boolean genderChoose = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interlocutor_info_layout);

        //init
        interlocutorFemaleBtn = findViewById(R.id.interlocutorFemaleBtn);
        interlocutorMaleBtn = findViewById(R.id.interlocutorMaleBtn);
        interlocutorAnonBtn = findViewById(R.id.interlocutorAnonBtn);
        inpFrom = findViewById(R.id.inpFrom);
        inpTo = findViewById(R.id.inpTo);
        StaticModels.interlocutorInfo = new InterlocutorInfo();
        StaticModels.interlocutorInfo.setObjectType("InterlocutorInfo");
    }


    public  void  interlocutorFemaleButtonPress(View view)
    {
        // active
        interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //gender
        StaticModels.interlocutorInfo.setGender("female");
        genderChoose = true;
        //
    }

    public  void  interlocutorMaleButtonPress(View view)
    {
        // active
        interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        genderChoose = true;
        //gender
        StaticModels.interlocutorInfo.setGender("male");
    }

    public  void  interlocutorAnonButtonPress(View view)
    {
        //active
        interlocutorAnonBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_active_drawable));
        // not active
        interlocutorFemaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        interlocutorMaleBtn.setBackground(this.getResources().getDrawable(R.drawable.gender_white_color_drawable));
        //genderChoose = true;
    }

    public void searchBtnPress(View view)
    {
        String localFrom = inpFrom.getText().toString().trim();
        String localTo = inpTo.getText().toString().trim();
        // text edit not empty
        if(!localFrom.equals("") && !localTo.trim().equals("") && genderChoose)
        {
            int localFromInteger = Integer.parseInt(localFrom);
            int localToInteger = Integer.parseInt(localTo);
            if(localFromInteger>100||localFromInteger<0||localToInteger>100||localToInteger<0 ||localFromInteger>=localToInteger)
            {
                Toast toast = Toast.makeText(this, "age must be more 0 and less 100", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                // info normal
                StaticModels.interlocutorInfo.setAgeFrom(localFrom);
                StaticModels.interlocutorInfo.setAgeTo(localTo);
                StartSocketConnection.webSocket.send(ObjectType.getJson(StaticModels.interlocutorInfo));
                Intent intent = new Intent(this, LoadingAnimationActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Fill in all the fields ", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}
