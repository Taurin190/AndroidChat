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
                    Log.d("DEBUG", "Value is: " + list);
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
