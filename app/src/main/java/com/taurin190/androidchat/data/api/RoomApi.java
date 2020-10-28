package com.taurin190.androidchat.data.api;

import com.taurin190.androidchat.domain.Room;

import java.util.List;

import io.reactivex.Observable;

public interface RoomApi {
    Observable<List<Room>> getRoomList();

    Observable<Room> getRoomDetail(int roomId);

    Observable<Boolean> sendMessage(int roomId, String message);

    Observable<Room> createRoom(final String title);
}
