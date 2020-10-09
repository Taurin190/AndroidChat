package com.taurin190.androidchat.ui.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.taurin190.androidchat.R;
import com.taurin190.androidchat.domain.Chat;

import java.util.List;

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

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChatViewHolder(inflater.inflate(R.layout.chat_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int position) {
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
