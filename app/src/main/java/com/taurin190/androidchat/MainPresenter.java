package com.taurin190.androidchat;

import rx.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {
    private MainRepository mainRepository;
    private MainContract.View mainView;
    public MainPresenter(MainRepository repository, MainContract.View view) {
        this.mainRepository = repository;
        this.mainView = view;
    }

    @Override
    public void loadRoomCollection() {
        mainRepository.getRoomCollection()
                .observeOn(Schedulers.newThread())
                .subscribe(roomCollection -> {
                    mainView.renderRoomCollection(roomCollection);
                });
    }
}
