/***
 * Obsolete class
 */
package com.example.mtchat_android.models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mtchat_android.R;
import com.example.mtchat_android.serverobjects.Message;

import java.util.ArrayList;
import java.util.List;

// MessageAdapter.java
public class MessageAdapter extends BaseAdapter {


    List<Message> messages = new ArrayList<Message>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }


    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if(message.getName().equals("fict"))
        {
            convertView = messageInflater.inflate(R.layout.new_uset_layout, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText()+" joined the chat");
        }
        else if (message.getName().toString().equals(StaticModels.userInfo.getName())) { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());

        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);holder.name.setText(StaticModels.ifRoomCreated.getNameInterlocutor());
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText()+"\n"+StaticModels.messageTime);
        }



        return convertView;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getSize() {
        return messages.size();
    }

}

class MessageViewHolder {
    public TextView name;
    public TextView messageBody;
}