package com.taurin190.androidchat.ui.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taurin190.androidchat.R;

import androidx.recyclerview.widget.RecyclerView;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView title;
    public TextView content;
    public TextView roomId;
    public LinearLayout card;
    public RoomViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        card = (LinearLayout) itemView.findViewById(R.id.card);
        roomId = (TextView) itemView.findViewById(R.id.roomId);
    }
}
