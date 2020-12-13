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
    public Observable<List<Room>> getRoomList() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference("rooms");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap<String, Object> roomHash = (HashMap<String, Object>) snapshot.getValue();
                    ArrayList list = new ArrayList();
                    Room room;
                    for (String key: roomHash.keySet()) {
                        room = new Room(
                                key,
                                "",
                                (String)((HashMap)roomHash.get(key)).get("title"),
                                "",
                                "",
                                new ArrayList());
                        list.add(room);
                    }
                    sub.onNext(list);
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
    public Observable<Room> getRoomDetail(String roomId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return Observable.create((sub) -> {
            DatabaseReference ref = database.getReference("rooms");
            ref.child(roomId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Room room;
                    HashMap<String, Object> roomHash = (HashMap<String, Object>) snapshot.getValue();
                    ArrayList originalList = (ArrayList) roomHash.get("chatList");
                    ArrayList chatList = new ArrayList();
                    if (originalList != null) {
                        Chat chat;
                        HashMap<String, String> message;
                        for (int i = 0; i < originalList.size(); i++) {
                            message = (HashMap<String, String>) originalList.get(i);
                            Log.d("DEBUG", "Chat Message is: " + message.get("message"));
                            chatList.add(new Chat(message.get("message")));
                        }
                    }
                    room = new Room(
                            (String) roomHash.get("roomId"),
                            (String) roomHash.get("imageUrl"),
                            (String) roomHash.get("title"),
                            (String) roomHash.get("lastMessage"),
                            (String) roomHash.get("lastUpdate"),
                            chatList);
                    sub.onNext(room);
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
