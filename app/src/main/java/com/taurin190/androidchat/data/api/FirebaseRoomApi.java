package com.taurin190.androidchat.data.api;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taurin190.androidchat.domain.Room;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class FirebaseRoomApi implements RoomApi {

    @Override
    public Observable<List<Room>> getRoomList() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference("rooms");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList roomList = snapshot.getValue(ArrayList.class);
                    sub.onNext(roomList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }

    @Override
    public Observable<Room> getRoomDetail(int roomId) {
        return null;
    }

    @Override
    public Observable<Boolean> sendMessage(int roomId, String message) {
        return null;
    }

    @Override
    public Observable<Room> createRoom(String title) {
        return null;
    }
}
