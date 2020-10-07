package com.taurin190.androidchat;

public interface ChatContract {
    interface View {
        void renderMessage(Room room);
        void showSentMessage(Room room);
        void notifySendFailure();
        String getMessage();
        void clearInputForm();
        Room getRoom();
    }
    interface Presenter {
        void loadRoomDetail(Room room);
    }
}
