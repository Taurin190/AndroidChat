package com.taurin190.androidchat.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.taurin190.androidchat.R
import com.taurin190.androidchat.databinding.ActivityRoomCreationBinding
import com.taurin190.androidchat.domain.Room
import com.taurin190.androidchat.presenter.RoomCreationContract
import com.taurin190.androidchat.presenter.RoomCreationPresenter
import com.taurin190.androidchat.repository.MainRepository
import com.taurin190.androidchat.ui.fragment.MainFragment
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
        val repository = MainRepository.getInstance()
        val presenter = RoomCreationPresenter(repository, this, AppSchedulerProvider());

        binding.button.setOnClickListener{
            presenter.createRoom(binding.textView.text.toString())
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