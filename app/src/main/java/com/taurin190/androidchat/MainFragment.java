package com.taurin190.androidchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

public class MainFragment extends Fragment implements MainContract.View  {
    private MainContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        MainRepository repository = new MainRepository();
        presenter = new MainPresenter(repository, this);
        presenter.loadRoomCollection();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void showEmptyCase() {

    }

    @Override
    public void renderRoomCollection(Collection<Room> room) {

    }
}
