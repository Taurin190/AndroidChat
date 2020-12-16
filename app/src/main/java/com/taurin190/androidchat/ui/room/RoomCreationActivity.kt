package com.taurin190.androidchat.ui.room

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.taurin190.androidchat.data.api.FirebaseRoomApi
import com.taurin190.androidchat.data.api.RoomApi
import com.taurin190.androidchat.databinding.ActivityRoomCreationBinding
import com.taurin190.androidchat.data.model.Room
import com.taurin190.androidchat.presenter.RoomCreationContract
import com.taurin190.androidchat.presenter.RoomCreationPresenter
import com.taurin190.androidchat.data.repository.MainRepository
import com.taurin190.androidchat.ui.chat.ChatActivity
import com.taurin190.androidchat.ui.main.MainFragment
import com.taurin190.androidchat.util.AppSchedulerProvider

class RoomCreationActivity : AppCompatActivity(), RoomCreationContract.View {
    private lateinit var binding: ActivityRoomCreationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomCreationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.title = "新規ルーム作成"
        val api: RoomApi = FirebaseRoomApi()
        val repository = MainRepository(api, AppSchedulerProvider())
        val presenter = RoomCreationPresenter(repository, this, AppSchedulerProvider());

        binding.button.setOnClickListener{
            presenter.createRoom(binding.editTextTextPersonName.text.toString())
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun showCreatingView() {

    }

    override fun showCreateCompletion(room: Room?) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(MainFragment.ROOM_DETAIL, room)
        startActivity(intent)
        finish()
    }
}