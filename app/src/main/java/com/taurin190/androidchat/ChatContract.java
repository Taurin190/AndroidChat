package com.taurin190.androidchat;

public interface ChatContract {
    interface View {
        void renderMessage(Room room);
    }
    interface Presenter {
        void loadRoomDetail(Room room);
    }
}
