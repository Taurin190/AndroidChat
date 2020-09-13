package com.taurin190.androidchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taurin190.androidchat.databinding.FragmentMainBinding;

import java.util.Collection;

public class MainFragment extends Fragment implements MainContract.View  {
    private MainContract.Presenter presenter;

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        MainRepository repository = new MainRepository();
        presenter = new MainPresenter(repository, this);
        presenter.loadRoomCollection();

        binding.textView.setText("Initial Text");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showEmptyCase() {

    }

    @Override
    public void renderRoomCollection(Collection<Room> room) {
        System.out.println();
        binding.textView.setText("Room Size" + room.size());
    }
}
