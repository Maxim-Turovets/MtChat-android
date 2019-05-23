package com.example.mtchat_android.models;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.serverobjects.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AdapterMessage extends BaseAdapter {


    List<MergedMessage> mergedMessages = new ArrayList<>();
    Context context;

    public AdapterMessage(Context context) {
        this.context = context;
    }

    public void add(MergedMessage message) {
        this.mergedMessages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return mergedMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return mergedMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MergedMessage message = mergedMessages.get(i);
        //TEXT MESSAGE
        if (message.getTextMessage() != null) {
            MessageViewHolder holder = new MessageViewHolder();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (message.getTextMessage().getName().equals("fict")) {
                convertView = messageInflater.inflate(R.layout.new_uset_layout, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());
            } else if (message.getTextMessage().getName().toString().equals(StaticModels.userInfo.getName())) { // this message was sent by us so let's create a basic chat bubble on the right
                convertView = messageInflater.inflate(R.layout.my_message, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());

            } else { // this message was sent by someone else so let's create an advanced chat bubble on the left

                convertView = messageInflater.inflate(R.layout.their_message, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.name.setText(StaticModels.interlocutorName);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());
            }
        }

        // IMAGE MESSAGE
        if (message.getImageMessage() != null) {
            MessageViewHolder2 holder = new MessageViewHolder2();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//            byte [] ar = message.getImageMessage().getByteArray();


            byte[] decodedString = Base64.decode(message.getImageMessage().getImage().toString().getBytes(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


            if (bmp!=null) {

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 10, out);

                byte[] byteArray = out.toByteArray();

                // MY IMAGE
                //   if (message.getImageMessage().isFromMe()) {
                // Convert bytes data into a Bitmap

                convertView = messageInflater.inflate(R.layout.image_my_message_layout, null);
                holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);
                holder.messageBody.setImageBitmap(bmp);

                //Вычисляем ширину и высоту изображения
                double width = bmp.getWidth();
                double height = bmp.getHeight();

                double koef = height / 1000;
                int newWidht = (int) (width / koef);
                int newheight = (int) (height / koef);

                Bitmap bmHalf = Bitmap.createScaledBitmap(bmp, newWidht, newheight, false);
                holder.messageBody.setImageBitmap(bmHalf);

                String FILENAME = "image.png";
                String PATH = "/mnt/sdcard/"+ FILENAME;
                File f = new File(PATH);
                Uri yourUri = Uri.fromFile(f);

            }
        }


        return convertView;
    }



    class MessageViewHolder {
        public TextView name;
        public TextView messageBody;
    }

    class MessageViewHolder2 {
        public ImageView messageBody;
    }


}