package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mtchat_android.R;

public class ImageLoad extends AppCompatActivity {

    ImageView foto;
    Button btn;
    private  static  final  int PICK_IMAGE =100;

    Uri imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_load);

        foto = (ImageView)findViewById(R.id.foto);
        btn = (Button)findViewById(R.id.btnLoad);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGal();
            }
        });

    }


    private  void openGal(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK&& requestCode==PICK_IMAGE)
            imageUrl = data.getData();
        foto.setImageURI(imageUrl);

        BitmapDrawable drawable = (BitmapDrawable) foto.getDrawable();
        Bitmap bitmap = drawable.getBitmap();


    }
}
