package com.example.mtchat_android.models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mtchat_android.R;

import java.util.ArrayList;

public class ImageMessageAdapter extends BaseAdapter {

    ArrayList<ImageMessage> imageMessages = new ArrayList<>();
    Context context;

    public ImageMessageAdapter(Context context) {
        this.context = context;
    }

    public void add(ImageMessage message) {
        this.imageMessages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return imageMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return imageMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MessageViewHolder2 holder = new MessageViewHolder2();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ImageMessage message = imageMessages.get(i);
        Bitmap bmp = BitmapFactory.decodeByteArray(message.getByteArray(), 0, message.getByteArray().length);


        if(message.isFromMe()) {
            // Convert bytes data into a Bitmap
            convertView = messageInflater.inflate(R.layout.image_my_message_layout, null);
            holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);
            holder.messageBody.setMaxHeight(100);
            holder.messageBody.setMaxWidth(100);
            convertView.setTag(holder);
            holder.messageBody.setImageBitmap(bmp);
        }
        else{
            // Convert bytes data into a Bitmap
            convertView = messageInflater.inflate(R.layout.image_their_message_layout, null);
            holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);
            convertView.setTag(holder);
            holder.messageBody.setMaxHeight(100);
            holder.messageBody.setMaxWidth(100);
            holder.messageBody.setImageBitmap(bmp);
        }

      return convertView;
    }


}

class MessageViewHolder2 {
    public ImageView messageBody;
}