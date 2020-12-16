package com.taurin190.androidchat.presentation.main;

import com.taurin190.androidchat.data.model.Room;

public interface MainFragmentListener {
    public void moveRoomDetail(Room room);

    public void showRoomCreationButton();
}
