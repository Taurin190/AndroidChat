package com.taurin190.androidchat.presenter;

import com.taurin190.androidchat.data.model.Room;

public interface RoomCreationContract {
    interface View {
        void showCreatingView();

        void showCreateCompletion(Room room);
    }

    interface Presenter {
        void createRoom(String title);
    }
}
