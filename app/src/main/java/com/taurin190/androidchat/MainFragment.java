package com.taurin190.androidchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;

public class MainFragment extends Fragment implements MainContract.View  {
    private MainContract.Presenter presenter;

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        MainRepository repository = new MainRepository();
        presenter = new MainPresenter(repository, this);
        presenter.loadRoomCollection();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("Initial Text");
        return view;
    }

    @Override
    public void showEmptyCase() {

    }

    @Override
    public void renderRoomCollection(Collection<Room> room) {
        System.out.println();
        textView.setText("Room Size" + room.size());
    }
}
