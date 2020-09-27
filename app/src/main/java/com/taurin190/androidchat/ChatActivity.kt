package com.taurin190.androidchat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val room: Room = intent.getSerializableExtra(MainFragment.ROOM_DETAIL) as Room
        actionBar.setTitle(room.getTitle())

        val fragment = ChatFragment.newInstance(room)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }
}