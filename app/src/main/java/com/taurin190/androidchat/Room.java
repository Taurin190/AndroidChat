package com.taurin190.androidchat;

import android.net.Uri;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Room {
    private int roomId;
    private Uri imageUrl;
    private String title;
    private String lastMessage;
    private String lastUpdate;
    private List<Chat> chatList;
}
