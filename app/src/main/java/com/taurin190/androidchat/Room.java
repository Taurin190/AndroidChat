package com.taurin190.androidchat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Room {
    private int roomId;
    private String imageUrl;
    private String title;
    private String lastMessage;
    private String lastUpdate;

}
