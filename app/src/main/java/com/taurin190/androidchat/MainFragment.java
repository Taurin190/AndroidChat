package com.taurin190.androidchat;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taurin190.androidchat.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {
    private MainContract.Presenter presenter;

    private FragmentMainBinding binding;

    private RoomListAdapter adapter;

    private Context context = null;

    private List<Room> roomList;

    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        handler = new Handler();
        MainRepository repository = new MainRepository();
        presenter = new MainPresenter(repository, this);
        presenter.loadRoomCollection();


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.roomList = new ArrayList<Room>();
        adapter = new RoomListAdapter(getActivity().getBaseContext(), this.roomList);
        RecyclerView listView = (RecyclerView) binding.roomListview;
        this.context = getActivity().getBaseContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
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
    public void renderRoomCollection(List<Room> room) {
        adapter.setRoomList(room);
    }
}
