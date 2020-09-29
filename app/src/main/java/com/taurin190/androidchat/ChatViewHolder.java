package com.taurin190.androidchat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView message;
    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        this.message = itemView.findViewById(R.id.message);
    }
}
