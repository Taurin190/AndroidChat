package com.taurin190.androidchat.data.repository;

import android.util.Log;

import com.taurin190.androidchat.data.api.RoomApi;
import com.taurin190.androidchat.data.model.Chat;
import com.taurin190.androidchat.data.model.Room;
import com.taurin190.androidchat.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class MainRepository {
    static MainRepository instance;
    private RoomApi api;
    private SchedulerProvider schedulerProvider;

    public MainRepository(RoomApi api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    public Observable<List<Room>> getRoomList() {
        return Observable.create((sub) -> {
            api.getRoomList()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(subject -> {
                        ArrayList list = new ArrayList();
                        Room room;
                        for (String key: subject.keySet()) {
                            room = new Room(
                                    key,
                                    "",
                                    (String)((HashMap)subject.get(key)).get("title"),
                                    "",
                                    "",
                                    new ArrayList());
                            list.add(room);
                        }
                        sub.onNext(list);
                        sub.onComplete();
                    });
        });
    }

    public Observable<Room> getRoomDetail(String roomId) {
        return Observable.create((sub) -> {
            this.api.getRoomDetail(roomId)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(subject -> {
                        ArrayList originalList = (ArrayList) subject.get("chatList");
                        ArrayList chatList = new ArrayList();
                        if (originalList != null) {
                            HashMap<String, String> message;
                            for (int i = 0; i < originalList.size(); i++) {
                                message = (HashMap<String, String>) originalList.get(i);
                                Log.d("DEBUG", "Chat Message is: " + message.get("message"));
                                chatList.add(new Chat(message.get("message")));
                            }
                        }
                        Room room = new Room(
                                (String) subject.get("roomId"),
                                (String) subject.get("imageUrl"),
                                (String) subject.get("title"),
                                (String) subject.get("lastMessage"),
                                (String) subject.get("lastUpdate"),
                                chatList);
                        sub.onNext(room);
                        sub.onComplete();
                    });
        });
    }

    public Observable<Room> sendMessage(Room room, String message) {
        return this.api.sendMessage(room, message);
    }

    public Observable<Room> createRoom(final String title) {
        return api.createRoom(title);
    }
}
