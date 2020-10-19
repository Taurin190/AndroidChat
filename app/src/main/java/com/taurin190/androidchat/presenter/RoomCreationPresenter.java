package com.taurin190.androidchat.presenter;

import com.taurin190.androidchat.repository.MainRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class RoomCreationPresenter implements RoomCreationContract.Presenter {
    private MainRepository repository;
    private RoomCreationContract.View view;
    public RoomCreationPresenter(MainRepository repository, RoomCreationContract.View view) {
        this.repository = repository;
        this.view = view;
    }
    @Override
    public void createRoom(String title) {
        view.showCreatingView();
        repository.createRoom(title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(room -> {
                    view.showCreateCompletion(room);
                });
    }
}
