package com.taurin190.androidchat.data.api;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taurin190.androidchat.domain.Chat;
import com.taurin190.androidchat.domain.Room;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import lombok.SneakyThrows;

public class FirebaseRoomApi implements RoomApi {

    @Override
    public Observable<HashMap<String, Object>> getRoomList() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference("rooms");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap<String, Object> roomHash = (HashMap<String, Object>) snapshot.getValue();
                    sub.onNext(roomHash);
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
    public Observable<HashMap<String, Object>> getRoomDetail(String roomId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference("rooms");
            ref.child(roomId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Room room;
                    HashMap<String, Object> roomHash = (HashMap<String, Object>) snapshot.getValue();
                    sub.onNext(roomHash);
                    sub.onComplete();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }

    @Override
    public Observable<Room> sendMessage(Room room, String message) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Room newRoom = room;
        newRoom.appendChatList(new Chat(message));
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference();
            ref.child("rooms").child(room.getRoomId()).setValue(newRoom)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            sub.onNext(newRoom);
                            sub.onComplete();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            sub.onNext(room);
                            sub.onComplete();
                        }
                    });
        });
    }

    @Override
    public Observable<Room> createRoom(String title) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference();
            String newPostKey = ref.child("rooms").push().getKey();
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Room room = new Room(
                    newPostKey,
                    "",
                    title,
                    "",
                    df.format(now),
                    new ArrayList<>());
            ref.child("rooms").child(newPostKey).setValue(room)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            sub.onNext(room);
                        }
                    });
        });
    }
}
