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

    public Observable<Room> getRoomDetail(int roomId) {
        return Observable.create((sub) -> {
            AsyncTask task = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<Chat> chatList = new ArrayList<>();
                    chatList.add(new Chat("aaa"));
                    chatList.add(new Chat("bbb"));
                    chatList.add(new Chat("ccc"));
                    Room roomDetail = new Room(
                            roomId,
                            "https://source.unsplash.com/user/erondu/1600x900",
                            "ROOM 1",
                            "最後のメッセージ",
                            "昨日",
                            chatList);
                    sub.onNext(roomDetail);
                    sub.onComplete();
                    return objects[0];
                }
            };
            task.execute(0);
        });
    }

    public Observable<Boolean> sendMessage(int roomId, String message) {
        return Observable.create((sub) -> {
            sub.onNext(false);
            sub.onComplete();
        });
    }

    public Observable<Room> createRoom(final String title) {
        return api.createRoom(title);
    }
}
