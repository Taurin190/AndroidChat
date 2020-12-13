package com.taurin190.androidchat.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.taurin190.androidchat.data.api.FirebaseRoomApi;
import com.taurin190.androidchat.data.api.RoomApi;
import com.taurin190.androidchat.data.repository.MainRepository;
import com.taurin190.androidchat.databinding.FragmentChatBinding;
import com.taurin190.androidchat.domain.Chat;
import com.taurin190.androidchat.domain.Room;
import com.taurin190.androidchat.presenter.ChatContract;
import com.taurin190.androidchat.presenter.ChatPresenter;
import com.taurin190.androidchat.ui.helper.ChatListAdapter;
import com.taurin190.androidchat.util.AppSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RoomApi api = new FirebaseRoomApi();
        MainRepository repository = new MainRepository(api, new AppSchedulerProvider());
        this.presenter = new ChatPresenter(repository, this, new AppSchedulerProvider());
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendMessage(room, binding.inputForm.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.loadRoomDetail(this.room);
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
        this.room = room;
        binding.centerMessage.setVisibility(View.GONE);
        binding.inputLayout.setVisibility(View.VISIBLE);
        binding.chatListview.setVisibility(View.VISIBLE);
        adapter.setChatList(room.getChatList());
    }

    @Override
    public void showSentMessage(Room room) {
        adapter.setChatList(room.getChatList());
        binding.chatListview.scrollToPosition(room.getChatList().size() - 1);
    }

    @Override
    public void notifySendFailure() {

    }

    @Override
    public void clearInputForm() {
        binding.inputForm.setText("");
        InputMethodManager imm = (InputMethodManager)this.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        binding.inputForm.clearFocus();
    }

}