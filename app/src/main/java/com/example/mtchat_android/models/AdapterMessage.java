package com.example.mtchat_android.models;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.io.ByteArrayOutputStream;

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
    ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

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

        final MergedMessage message = mergedMessages.get(i);
        //TEXT MESSAGE
        if (message.getTextMessage() != null) {
            MessageViewHolder holder = new MessageViewHolder();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (message.getTextMessage().getName().equals("fict")) {
                convertView = messageInflater.inflate(R.layout.new_uset_layout, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());
            }
            else if (message.getTextMessage().getName().toString().equals(StaticModels.userInfo.getName())) { // this message was sent by us so let's create a basic chat bubble on the right
                convertView = messageInflater.inflate(R.layout.my_message, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());

            }
            else { // this message was sent by someone else so let's create an advanced chat bubble on the left

                convertView = messageInflater.inflate(R.layout.their_message, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.name.setText(StaticModels.interlocutorName);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());
                holder.name.setText(message.getTextMessage().getName());
            }
        }

        // IMAGE MESSAGE
        if (message.getImageMessage() != null) {
            final MessageViewHolder2 holder = new MessageViewHolder2();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//            byte [] ar = message.getImageMessage().getByteArray();


            final Bitmap[] link = {null};

            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] decodedString = Base64.decode(message.getImageMessage().getImage().toString().getBytes(), Base64.DEFAULT);
                    final Bitmap bmp  = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    link[0] = bmp;
                }
            }).run();



            if (link[0] !=null) {






                // MY IMAGE
                //   if (message.getImageMessage().isFromMe()) {
                // Convert bytes data into a Bitmap

                convertView = messageInflater.inflate(R.layout.image_my_message_layout, null);
                holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);
                //holder.messageBody.setImageBitmap(bmp);





                        final double width = link[0].getWidth();
                        double height = link[0].getHeight();

                        double koef = height / 1000;
                        final int newWidht = (int) (width / koef);
                        final int newheight = (int) (height / koef);



                new Thread(new Runnable() {
                    public void run() {
                        final  Bitmap bmHalf = Bitmap.createScaledBitmap(link[0], newWidht, newheight, false);

                                holder.messageBody.setImageBitmap(bmHalf);

                    }
                }).run();







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