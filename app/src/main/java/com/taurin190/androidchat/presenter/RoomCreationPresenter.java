package com.taurin190.androidchat.presenter;

import com.taurin190.androidchat.data.repository.MainRepository;
import com.taurin190.androidchat.util.SchedulerProvider;

public class RoomCreationPresenter implements RoomCreationContract.Presenter {
    private MainRepository repository;
    private RoomCreationContract.View view;
    private SchedulerProvider schedulerProvider;
    public RoomCreationPresenter(MainRepository repository, RoomCreationContract.View view, SchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
    }
    @Override
    public void createRoom(String title) {
        view.showCreatingView();
        repository.createRoom(title)
                .observeOn(this.schedulerProvider.ui())
                .subscribe(room -> {
                    view.showCreateCompletion(room);
                });
    }
}
