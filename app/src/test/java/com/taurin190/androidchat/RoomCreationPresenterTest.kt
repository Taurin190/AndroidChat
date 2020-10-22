package com.taurin190.androidchat

import com.taurin190.androidchat.domain.Room
import com.taurin190.androidchat.presenter.RoomCreationContract
import com.taurin190.androidchat.presenter.RoomCreationPresenter
import com.taurin190.androidchat.repository.MainRepository
import com.taurin190.androidchat.util.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class RoomCreationPresenterTest {
    @Test
    fun testCreateRoom() {
        val repository = Mockito.mock(MainRepository::class.java)
        val view = Mockito.mock(RoomCreationContract.View::class.java)
        val presenter = RoomCreationPresenter(repository, view, TestSchedulerProvider())
        val room = Mockito.mock(Room::class.java)

        `when`(repository.createRoom("TEST ROOM")).thenReturn(
                Observable.create(
                        ObservableOnSubscribe {
                            sub: ObservableEmitter<Room> -> sub.onNext(room)
                        }
                )
        )

        presenter.createRoom("TEST ROOM")

        verify(view).showCreatingView()
        verify(repository).createRoom("TEST ROOM")
        verify(view).showCreateCompletion(room)
    }
}