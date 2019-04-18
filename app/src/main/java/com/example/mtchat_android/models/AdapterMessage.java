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

        if (message.getTextMessage() != null) {
            MessageViewHolder holder = new MessageViewHolder();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (message.getTextMessage().getName().equals("fict")) {
                convertView = messageInflater.inflate(R.layout.new_uset_layout, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText() + " joined the chat");
            } else if (message.getTextMessage().getName().toString().equals(StaticModels.userInfo.getName())) { // this message was sent by us so let's create a basic chat bubble on the right
                convertView = messageInflater.inflate(R.layout.my_message, null);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText());

            } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
                convertView = messageInflater.inflate(R.layout.their_message, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.name.setText(StaticModels.ifRoomCreated.getNameInterlocutor());
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getTextMessage().getText() + "\n" + StaticModels.messageTime);
            }
        }
        if (message.getImageMessage() != null) {
            MessageViewHolder2 holder = new MessageViewHolder2();
            LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            Bitmap bmp = BitmapFactory.decodeByteArray(message.getImageMessage().getByteArray(), 0, message.getImageMessage().getByteArray().length);


            if (message.getImageMessage().isFromMe()) {
                // Convert bytes data into a Bitmap
                convertView = messageInflater.inflate(R.layout.image_my_message_layout, null);
                holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);

                // Вычисляем ширину и высоту изображения
                int width = bmp.getWidth();
                int height = bmp.getHeight();

                if (width>1000) {
                    if (width > height) {
                        int temp_int = width;
                        width = height;
                        height = temp_int;

                        if (width > 1000) {
                            if (height > width) {
                                int t = width / 1000;
                                width =1000;
                                height = height/ t;
                                Bitmap bmHalf = Bitmap.createScaledBitmap(bmp, height,
                                        width, false);
                                holder.messageBody.setImageBitmap(bmHalf);
                            }

                        }
                        holder.messageBody.setRotation(90);
                    }else {
                        int t = width - 1000;
                        Bitmap bmHalf = Bitmap.createScaledBitmap(bmp, 1000, height - t, false);
                        holder.messageBody.setImageBitmap(bmHalf);

                    }
                } else {
                    holder.messageBody.setImageBitmap(bmp);
                }

            } else {
                // Convert bytes data into a Bitmap
                convertView = messageInflater.inflate(R.layout.image_their_message_layout, null);
                holder.messageBody = (ImageView) convertView.findViewById(R.id.imageMessageView);
                convertView.setTag(holder);
                // Вычисляем ширину и высоту изображения
                int width = bmp.getWidth();
                int height = bmp.getHeight();

                if (width>1000) {
                    if (width > height) {
                        int temp_int = width;
                        width = height;
                        height = temp_int;

                        if (width > 1000) {
                            if (height > width) {
                                int t = width / 1000;
                                width =1000;
                                height = height/ t;
                                Bitmap bmHalf = Bitmap.createScaledBitmap(bmp, height,
                                        width, false);
                                holder.messageBody.setImageBitmap(bmHalf);
                            }

                        }
                        holder.messageBody.setRotation(90);
                    }else {
                        int t = width - 1000;
                        Bitmap bmHalf = Bitmap.createScaledBitmap(bmp, 1000, height - t, false);
                        holder.messageBody.setImageBitmap(bmHalf);

                    }
                } else {
                    holder.messageBody.setImageBitmap(bmp);
                }
            }

            holder.messageBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setRotation(90);
                }
            });
        }


        return convertView;
    }

    //    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }
//
//    public int getSize() {
//        return messages.size();
//    }
    class MessageViewHolder {
        public TextView name;
        public TextView messageBody;
    }


}

