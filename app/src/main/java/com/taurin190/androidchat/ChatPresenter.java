package com.taurin190.androidchat;

import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChatPresenter implements ChatContract.Presenter, View.OnClickListener {
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

    @Override
    public void onClick(View v) {
        Room room = chatView.getRoom();
        room.appendChatList(new Chat(chatView.getMessage()));
        chatView.showSentMessage(room);
        chatView.clearInputForm();
    }
}
