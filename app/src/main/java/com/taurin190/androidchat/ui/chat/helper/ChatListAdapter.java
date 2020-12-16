package com.taurin190.androidchat.ui.chat.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.taurin190.androidchat.R;
import com.taurin190.androidchat.data.model.Chat;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private LayoutInflater inflater;
    private List<Chat> list;

    public ChatListAdapter(Context context, List<Chat> chatList) {
        this.inflater = LayoutInflater.from(context);
        this.list = chatList;
    }

    public void setChatList(List<Chat> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChatViewHolder(inflater.inflate(R.layout.chat_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ChatViewHolder chatViewHolder, int position) {
        if (list.size() == 0) {
            return;
        }
        chatViewHolder.message.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
