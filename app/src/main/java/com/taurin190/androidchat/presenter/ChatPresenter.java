package com.taurin190.androidchat.presenter;

import com.taurin190.androidchat.data.repository.MainRepository;
import com.taurin190.androidchat.domain.Chat;
import com.taurin190.androidchat.domain.Room;
import com.taurin190.androidchat.util.SchedulerProvider;

public class ChatPresenter implements ChatContract.Presenter {
    private MainRepository repository;
    private ChatContract.View chatView;
    private SchedulerProvider schedulerProvider;

    public ChatPresenter(MainRepository repository, ChatContract.View view, SchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.chatView = view;
        this.schedulerProvider = schedulerProvider;
    }
    @Override
    public void loadRoomDetail(Room room) {
        this.repository.getRoomDetail(room.getRoomId())
                .observeOn(this.schedulerProvider.ui())
                .subscribe(detailRoom -> {
                    this.chatView.renderMessage(detailRoom);
                });
    }

    @Override
    public void sendMessage(Room room, String message) {
        this.repository.sendMessage(room, message)
                .observeOn(this.schedulerProvider.ui())
                .subscribe(newRoom -> {
                    chatView.showSentMessage(newRoom);
                    chatView.clearInputForm();
                });
    }
}
