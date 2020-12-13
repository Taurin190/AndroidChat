package com.taurin190.androidchat.data.repository;

import android.os.AsyncTask;

import com.taurin190.androidchat.data.api.RoomApi;
import com.taurin190.androidchat.domain.Chat;
import com.taurin190.androidchat.domain.Room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainRepository {
    static MainRepository instance;
    private RoomApi api;

    public MainRepository(RoomApi api) {
        this.api = api;
    }

    public Observable<List<Room>> getRoomList() {
        return api.getRoomList();
    }

    public Observable<Room> getRoomDetail(String roomId) {
        return this.api.getRoomDetail(roomId);
    }

    public Observable<Room> sendMessage(Room room, String message) {
        return this.api.sendMessage(room, message);
    }

    public Observable<Room> createRoom(final String title) {
        return api.createRoom(title);
    }
}
