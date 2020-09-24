package com.taurin190.androidchat;

import java.util.List;

public interface MainContract {
    interface View {
        void showEmptyCase();
        void renderRoomCollection(final List<Room> room);
    }

    interface Presenter {
        void loadRoomCollection();
    }
}
