package com.taurin190.androidchat.presentation.main;

import com.taurin190.androidchat.data.model.Room;

import java.util.List;

public interface MainContract {
    interface View {
        void showEmptyCase();
        void renderRoomCollection(final List<Room> room);
        void moveRoomDetail(String roomId);
    }

    interface Presenter {
        void loadRoomCollection();
    }
}
