package com.taurin190.androidchat.presenter;

import com.taurin190.androidchat.data.model.Room;

public interface ChatContract {
    interface View {
        void renderMessage(Room room);
        void showSentMessage(Room room);
        void notifySendFailure();
        void clearInputForm();
    }
    interface Presenter {
        void loadRoomDetail(Room room);
        void sendMessage(Room room, String newMessage);
    }
}
