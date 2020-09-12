package com.taurin190.androidchat;

import lombok.Getter;

@Getter
public class Room {
    private int roomId;
    private String imageUrl;
    private String title;
    private String lastMessage;
    private String lastUpdate;
}
