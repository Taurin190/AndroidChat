package com.taurin190.androidchat

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.function.Predicate

class MainActivity : AppCompatActivity(), MainFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()
    }

    override fun moveRoomDetail(room: Room?) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(MainFragment.ROOM_DETAIL, room)
        startActivity(intent)
    }
}
