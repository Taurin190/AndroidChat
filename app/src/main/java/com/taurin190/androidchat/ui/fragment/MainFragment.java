package com.taurin190.androidchat.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taurin190.androidchat.presenter.MainContract;
import com.taurin190.androidchat.presenter.MainPresenter;
import com.taurin190.androidchat.repository.MainRepository;
import com.taurin190.androidchat.databinding.FragmentMainBinding;
import com.taurin190.androidchat.domain.Room;
import com.taurin190.androidchat.ui.helper.RoomListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {
    public static final String ROOM_DETAIL = "room_detail";

    private MainContract.Presenter presenter;

    private FragmentMainBinding binding;

    private RoomListAdapter adapter;

    private Context context = null;

    private List<Room> roomList;

    private MainFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MainRepository repository = MainRepository.getInstance();
        presenter = new MainPresenter(repository, this);
        presenter.loadRoomCollection();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.roomList = new ArrayList<Room>();
        adapter = new RoomListAdapter(getActivity().getBaseContext(), this.roomList);
        adapter.setOnItemClickListener((View.OnClickListener) presenter);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentListener) {
            listener = (MainFragmentListener) context;
        }
    }

    @Override
    public void showEmptyCase() {
        binding.progressBar.setVisibility(View.GONE);
        binding.emptyMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderRoomCollection(List<Room> room) {
        binding.centerMessage.setVisibility(View.GONE);
        adapter.setRoomList(room);
    }

    @Override
    public void moveRoomDetail(int roomId) {
        Room room = roomList.stream().filter(r ->
                    roomId == r.getRoomId()
                ).findFirst().orElse(null);
        if (listener != null) {
            listener.moveRoomDetail(room);
        }
    }
}
