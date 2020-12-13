package com.taurin190.androidchat.data.api;

import com.taurin190.androidchat.domain.Room;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface RoomApi {
    Observable<HashMap<String, Object>> getRoomList();

    Observable<HashMap<String, Object>> getRoomDetail(String roomId);

    Observable<Room> sendMessage(Room room, String message);

    Observable<Room> createRoom(final String title);
}
