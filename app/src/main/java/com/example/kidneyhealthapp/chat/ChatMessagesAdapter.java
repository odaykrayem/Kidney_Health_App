package com.example.kidneyhealthapp.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.kidneyhealthapp.R;
import com.example.kidneyhealthapp.chat.models.BaseMessage;
import com.example.kidneyhealthapp.model.Message;
import com.example.kidneyhealthapp.utils.SharedPrefManager;

import java.util.ArrayList;

public class ChatMessagesAdapter extends RecyclerView.Adapter {

    public static final int MESSAGE_FROM_OTHER = 1;
    public static final int MESSAGE_FROM_ME = 0;

    ArrayList<Message> mMessageList;
    Context context;


    public ChatMessagesAdapter(ArrayList<Message> mMessageList, Context context) {
        this.mMessageList = mMessageList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Message message =  mMessageList.get(position);

        if(message.isFromMe()){
            return MESSAGE_FROM_ME;
        }else {
            return MESSAGE_FROM_OTHER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if (viewType == MESSAGE_FROM_ME) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_me, parent, false);
            return new ViewHolderFromMe(view);
        } else if (viewType == MESSAGE_FROM_OTHER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_other, parent, false);
            return new ViewHolderFromOther(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Message message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case MESSAGE_FROM_ME:
                ((ViewHolderFromMe) holder).bind(message);
                break;
            case MESSAGE_FROM_OTHER:
                ((ViewHolderFromOther) holder).bind(message);
        }

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ViewHolderFromMe extends RecyclerView.ViewHolder {

        public TextView message;

        public ViewHolderFromMe(View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.text_chat_message_me);
        }

        void bind(Message message) {
            this.message.setText(message.getContent());
        }
    }

    public class ViewHolderFromOther extends RecyclerView.ViewHolder {

        public TextView message;

        public ViewHolderFromOther(View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.text_chat_message_other);
        }
        void bind(Message message) {
            this.message.setText(message.getContent());
        }
    }
}
