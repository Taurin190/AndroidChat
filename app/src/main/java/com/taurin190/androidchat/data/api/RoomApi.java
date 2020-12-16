package com.taurin190.androidchat.data.api;

import com.taurin190.androidchat.data.model.Room;

import java.util.HashMap;

import io.reactivex.Observable;

public interface RoomApi {
    Observable<HashMap<String, Object>> getRoomList();

    Observable<HashMap<String, Object>> getRoomDetail(String roomId);

    Observable<Room> sendMessage(Room room, String message);

    Observable<Room> createRoom(final String title);
}
