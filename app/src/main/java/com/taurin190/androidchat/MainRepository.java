package com.taurin190.androidchat;

import android.os.AsyncTask;

import rx.Observable;

import java.util.ArrayList;
import java.util.Collection;

public class MainRepository {
    public Observable<Collection<Room>> getRoomCollection() {
        return Observable.create((sub) -> {
            AsyncTask task = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ArrayList<Room> roomList = new ArrayList<>();
                    roomList.add(new Room(
                            1,
                            "http://test.com/a.jpg",
                            "ROOM 1",
                            "最後のメッセージ",
                            "昨日"));
                    sub.onNext(roomList);
                    sub.onCompleted();
                    return objects[0];
                }
            };
            task.execute(0);
        });
    }
}
