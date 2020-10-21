package com.taurin190.androidchat

import com.taurin190.androidchat.domain.Room
import com.taurin190.androidchat.presenter.ChatContract
import com.taurin190.androidchat.presenter.ChatPresenter
import com.taurin190.androidchat.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class ChatPresenterTest {
    @Test
    fun testLoadRoomDetail() {
        val repository = Mockito.mock(MainRepository::class.java)
        val view = Mockito.mock(ChatContract.View::class.java)
        val room = Room(100,
                "https://123.jpg",
                "テストルーム",
                "最後のメッセージ",
                "昨日",
                arrayListOf()
        )
        val presenter = ChatPresenter(repository, view)
        `when`(repository.getRoomDetail(100)).thenReturn(
                Observable.create(
                        ObservableOnSubscribe {
                            sub: ObservableEmitter<Room> -> sub.onNext(room)
                        }
                )
        )

        presenter.loadRoomDetail(room)
        verify(repository).getRoomDetail(100)
        verify(view).renderMessage(room)
    }
}