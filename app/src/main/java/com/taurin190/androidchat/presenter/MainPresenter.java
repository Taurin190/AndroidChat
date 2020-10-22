package com.taurin190.androidchat.presenter;

import android.view.View;

import com.taurin190.androidchat.repository.MainRepository;
import com.taurin190.androidchat.util.SchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;

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
