package com.taurin190.androidchat;

import java.util.Collection;

public interface MainContract {
    interface View {
        void showEmptyCase();
        void renderRoomCollection(final Collection<Room> room);
    }

    interface Presenter {
        void loadRoomCollection();
    }
}
