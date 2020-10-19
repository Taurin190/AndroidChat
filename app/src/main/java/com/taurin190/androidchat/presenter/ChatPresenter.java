package com.taurin190.androidchat.presenter;

import android.view.View;

import com.taurin190.androidchat.repository.MainRepository;
import com.taurin190.androidchat.domain.Chat;
import com.taurin190.androidchat.domain.Room;

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

    @Override
    public void sendMessage(Room room, String message) {
        room.appendChatList(new Chat(message));
        chatView.showSentMessage(room);
        chatView.clearInputForm();
    }
}
