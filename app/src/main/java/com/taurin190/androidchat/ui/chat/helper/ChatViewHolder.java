package com.taurin190.androidchat.ui.chat.helper;

import android.view.View;
import android.widget.TextView;

import com.taurin190.androidchat.R;

import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView message;
    public ChatViewHolder(View itemView) {
        super(itemView);
        this.message = itemView.findViewById(R.id.message);
    }
}
