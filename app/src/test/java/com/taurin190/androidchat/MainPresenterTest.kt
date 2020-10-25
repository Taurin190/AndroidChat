package com.taurin190.androidchat

import com.taurin190.androidchat.domain.Room
import com.taurin190.androidchat.presenter.MainContract
import com.taurin190.androidchat.presenter.MainPresenter
import com.taurin190.androidchat.data.repository.MainRepository
import com.taurin190.androidchat.util.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.ArrayList

class MainPresenterTest {
    @Test
    fun testLoadRoomCollection() {
        val repository = Mockito.mock(MainRepository::class.java)
        val view = Mockito.mock(MainContract.View::class.java)
        val roomList: ArrayList<Room> = Mockito.mock(ArrayList::class.java) as ArrayList<Room>
        `when`(repository.roomList).thenReturn(
                Observable.create(
                        ObservableOnSubscribe {
                            sub: ObservableEmitter<List<Room>> -> sub.onNext(roomList)
                        }
                )
        )

        val presenter = MainPresenter(repository, view, TestSchedulerProvider())

        presenter.loadRoomCollection()

        verify(repository).roomList
        verify(view).renderRoomCollection(roomList)
    }
}