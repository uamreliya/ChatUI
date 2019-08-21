package me.jump360.chatui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.jump360.chatui.EmojiOnClickListener;
import me.jump360.chatui.Model.Message;
import me.jump360.chatui.R;

public class MessageListAdapter extends RecyclerView.Adapter {

    private static final int SENT = 1;
    private static final int RECEIVED = 2;

    private Context mContext;
    private List<Message> messageList;
    private EmojiOnClickListener listener;

    public MessageListAdapter(Context mContext, List<Message> messageList, EmojiOnClickListener listener) {
        this.mContext = mContext;
        this.messageList = messageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if (i == SENT) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.sent_message_layout, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (i == RECEIVED) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.received_message_layout, viewGroup, false);
            return new ReceivedMessageHolder(view, listener);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        if(message.getUser().getName().equalsIgnoreCase("Udit")){
            return SENT;
        }else {
            return RECEIVED;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Message message = messageList.get(i);

        switch (viewHolder.getItemViewType()) {

            case SENT:
                ((SentMessageHolder) viewHolder).bind(message);
                break;
            case RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(message);
                break;
        }

    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView textViewTime, textViewMessage;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.tv_sent_time);
            textViewMessage = itemView.findViewById(R.id.tv_sent_message);
        }

        void bind(Message message){

            textViewTime.setText(message.getTime());
            textViewMessage.setText(message.getMessage());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        TextView textViewTime, textViewMessage, textViewName;
        ImageView imageViewProfile;
        EmojiOnClickListener listener;

        public ReceivedMessageHolder(@NonNull View itemView, EmojiOnClickListener listener) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.tv_received_time);
            textViewMessage = itemView.findViewById(R.id.tv_received_message);
            textViewName = itemView.findViewById(R.id.tv_sender_name);
            imageViewProfile = itemView.findViewById(R.id.iv_profile);
            this.listener = listener;
        }

        void bind(final Message message){

            textViewTime.setText(message.getTime());
            textViewMessage.setText(message.getMessage());
            textViewName.setText(message.getUser().getName()+",");

            textViewMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEmojiClickListener(message, itemView);
                }
            });
        }
    }
}
