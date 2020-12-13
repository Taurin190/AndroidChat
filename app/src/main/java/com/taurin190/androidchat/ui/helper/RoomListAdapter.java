package com.taurin190.androidchat.ui.helper;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taurin190.androidchat.R;
import com.taurin190.androidchat.domain.Room;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomListAdapter extends RecyclerView.Adapter<RoomViewHolder> {

    private LayoutInflater inflater;
    private List<Room> list;
    private Context context;
    private View.OnClickListener listener;

    public RoomListAdapter(Context context, List<Room> roomList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        list = roomList;
    }

    public void setRoomList(List<Room> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RoomViewHolder(inflater.inflate(R.layout.room_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RoomViewHolder roomViewHolder, int position) {
        if (list.size() == 0) {
            return;
        }
        roomViewHolder.icon.setImageResource(R.mipmap.ic_launcher);
        roomViewHolder.title.setText(list.get(position).getTitle());
        roomViewHolder.content.setText(list.get(position).getLastMessage());
        roomViewHolder.roomId.setText(list.get(position).getRoomId());
        roomViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
