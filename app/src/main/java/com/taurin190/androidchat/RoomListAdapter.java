package com.taurin190.androidchat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomViewHolder> {

    private LayoutInflater inflater;
    private List<Room> list;
    private Context context;
    private int resource;

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

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RoomViewHolder(inflater.inflate(R.layout.room_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int position) {
        if (list.size() == 0) {
            return;
        }
        roomViewHolder.icon.setImageResource(R.mipmap.ic_launcher);
        roomViewHolder.title.setText(list.get(position).getTitle());
        roomViewHolder.content.setText(list.get(position).getLastMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
