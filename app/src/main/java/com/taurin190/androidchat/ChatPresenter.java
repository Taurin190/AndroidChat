package com.taurin190.androidchat;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChatPresenter implements ChatContract.Presenter {
    private MainRepository repository;
    private ChatContract.View chatView;

    public ChatPresenter(MainRepository repository, ChatContract.View view) {
        this.repository = repository;
        this.chatView = view;
    }
    @Override
    public void loadRoomDetail(Room room) {
        this.repository.getRoomDetail(room.getRoomId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailRoom -> {
                    this.chatView.renderMessage(detailRoom);
                });
    }
}
