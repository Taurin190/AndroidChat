package com.taurin190.androidchat.data.api;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taurin190.androidchat.domain.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import lombok.SneakyThrows;

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
                    if (roomList == null) {
                        roomList = new ArrayList();
                    }
                    Log.d("DEBUG", "Value is: " + roomList);
                    sub.onNext(roomList);
                    sub.onComplete();
                }

                @SneakyThrows
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Error");
                    sub.onError(error.toException());
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference();
            ref.child("rooms").child("1").child("title").setValue(title)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Room room = new Room(
                                    10,
                                    "https://source.unsplash.com/user/erondu/1600x900",
                                    title,
                                    "",
                                    "昨日",
                                    new ArrayList<>());
                            sub.onNext(room);
                        }
                    });
        });
    }
}
