package com.taurin190.androidchat.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taurin190.androidchat.R;
import com.taurin190.androidchat.data.api.FirebaseRoomApi;
import com.taurin190.androidchat.data.api.RoomApi;
import com.taurin190.androidchat.databinding.FragmentMainBinding;
import com.taurin190.androidchat.data.model.Room;
import com.taurin190.androidchat.presentation.room.helper.RoomListAdapter;
import com.taurin190.androidchat.util.AppSchedulerProvider;
import com.taurin190.androidchat.data.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RoomApi api = new FirebaseRoomApi();
        MainRepository repository = new MainRepository(api, new AppSchedulerProvider());
        presenter = new MainPresenter(repository, this, new AppSchedulerProvider());

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.loadRoomCollection();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.roomList = new ArrayList<Room>();
        adapter = new RoomListAdapter(getActivity().getBaseContext(), this.roomList);
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView roomId = (TextView) v.findViewById(R.id.roomId);
                Log.d("DEBUG", "Room ID is: " + roomId.getText().toString());
                moveRoomDetail(roomId.getText().toString());
            }
        });
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
        if (listener != null) {
            listener.showRoomCreationButton();
        }
    }

    @Override
    public void renderRoomCollection(List<Room> room) {
        binding.centerMessage.setVisibility(View.GONE);
        if (listener != null) {
            listener.showRoomCreationButton();
        }
        adapter.setRoomList(room);
    }

    @Override
    public void moveRoomDetail(String roomId) {
        Room room = roomList.stream().filter(r ->
                    roomId == r.getRoomId()
                ).findFirst().orElse(null);
        if (listener != null) {
            listener.moveRoomDetail(room);
        }
    }
}
