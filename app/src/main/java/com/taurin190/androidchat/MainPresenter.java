package com.taurin190.androidchat;

public class MainPresenter implements MainContract.Presenter {
    private MainInteractor mainInteractor;
    private MainContract.View mainView;
    public MainPresenter(MainInteractor interactor, MainContract.View view) {
        this.mainInteractor = interactor;
        this.mainView = view;
    }
}
