package com.taurin190.androidchat.presentation.main;

import com.taurin190.androidchat.data.repository.MainRepository;
import com.taurin190.androidchat.util.SchedulerProvider;

public class MainPresenter implements MainContract.Presenter {
    private MainRepository mainRepository;
    private MainContract.View mainView;
    private SchedulerProvider schedulerProvider;
    public MainPresenter(MainRepository repository, MainContract.View view, SchedulerProvider schedulerProvider) {
        this.mainRepository = repository;
        this.mainView = view;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void loadRoomCollection() {
        mainRepository.getRoomList()
                .observeOn(this.schedulerProvider.ui())
                .subscribe(roomList -> {
                    mainView.renderRoomCollection(roomList);
                });
    }
}
