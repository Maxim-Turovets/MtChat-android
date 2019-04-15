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
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.serverobjects.Message;

import java.util.ArrayList;
import java.util.List;

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

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MessageViewHolder2 holder = new MessageViewHolder2();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ImageMessage message = imageMessages.get(i);


        // Convert bytes data into a Bitmap
        Bitmap bmp = BitmapFactory.decodeByteArray(message.getByteArray(), 0, message.getByteArray().length);
        convertView = messageInflater.inflate(R.layout.image_message_layout, null);
        holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);


        convertView.setTag(holder);
        holder.messageBody.setImageBitmap(bmp);

      return convertView;
    }


}

class MessageViewHolder2 {
    public ImageView messageBody;
}