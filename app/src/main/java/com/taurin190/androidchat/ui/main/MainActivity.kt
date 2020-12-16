package com.taurin190.androidchat.ui.main

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.taurin190.androidchat.R
import com.taurin190.androidchat.databinding.ActivityMainBinding
import com.taurin190.androidchat.data.model.Room
import com.taurin190.androidchat.ui.chat.ChatActivity
import com.taurin190.androidchat.ui.room.RoomCreationActivity

class MainActivity : AppCompatActivity(), MainFragmentListener {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, RoomCreationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun moveRoomDetail(room: Room?) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(MainFragment.ROOM_DETAIL, room)
        startActivity(intent)
    }

    override fun showRoomCreationButton() {
        (binding.floatingActionButton as View).visibility = View.VISIBLE
    }
}
