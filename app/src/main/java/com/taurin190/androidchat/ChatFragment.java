package com.taurin190.androidchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taurin190.androidchat.databinding.FragmentChatBinding;
import com.taurin190.androidchat.databinding.FragmentMainBinding;

public class ChatFragment extends Fragment implements ChatContract.View {
    private static final String ARG_PARAM = "room";

    private ChatContract.Presenter presenter;

    private Room room;

    private FragmentChatBinding binding;

    public ChatFragment() {
    }

    public static ChatFragment newInstance(Room room) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, room);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.room = (Room) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MainRepository repository = MainRepository.getInstance();
        this.presenter = new ChatPresenter(repository, this);
        this.presenter.loadRoomDetail(this.room);
        return view;
    }

    @Override
    public void renderMessage(Room room) {
        binding.textview.setText(room.getLastMessage());
    }
}