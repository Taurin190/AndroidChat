package com.taurin190.androidchat;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Room implements Serializable {
    private int roomId;
    private String imageUrl;
    private String title;
    private String lastMessage;
    private String lastUpdate;
    private List<Chat> chatList;

    // ToDo remove this method. temporary added because lombok seems not to work from kotlin
    public String getTitle() {
        return title;
    }
}
