package com.taurin190.androidchat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taurin190.androidchat.databinding.FragmentChatBinding;
import com.taurin190.androidchat.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment implements ChatContract.View {
    private static final String ARG_PARAM = "room";

    private ChatContract.Presenter presenter;

    private Room room;

    private FragmentChatBinding binding;

    private ChatListAdapter adapter;

    private List<Chat> chatList;

    private Context context;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.context = getActivity().getBaseContext();
        this.chatList = new ArrayList<Chat>();
        adapter = new ChatListAdapter(this.context, this.chatList);
        RecyclerView listView = (RecyclerView) binding.chatListview;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
    }

    @Override
    public void renderMessage(Room room) {
        binding.centerMessage.setVisibility(View.GONE);
        adapter.setChatList(room.getChatList());
    }
}