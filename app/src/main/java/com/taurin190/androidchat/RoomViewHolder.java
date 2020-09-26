package com.taurin190.androidchat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView title;
    public TextView content;
    public LinearLayout card;
    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        card = (LinearLayout) itemView.findViewById(R.id.card);
    }
}
