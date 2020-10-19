package com.taurin190.androidchat.presenter;

import android.view.View;

import com.taurin190.androidchat.repository.MainRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainPresenter implements MainContract.Presenter {
    private MainRepository mainRepository;
    private MainContract.View mainView;
    public MainPresenter(MainRepository repository, MainContract.View view) {
        this.mainRepository = repository;
        this.mainView = view;
    }

    @Override
    public void loadRoomCollection() {
        mainRepository.getRoomList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roomList -> {
                    mainView.renderRoomCollection(roomList);
                });
    }
}
