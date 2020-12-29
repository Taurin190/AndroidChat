package com.taurin190.androidchat.presentation.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.taurin190.androidchat.util.SchedulerProvider
import java.util.concurrent.Executor

class LoginPresenter(
        private val auth: FirebaseAuth,
        private val view: LoginContract.View,
        private val executor: Executor): LoginContract.Presenter{
    override fun login(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(executor)
                { task ->
                    if (task.isSuccessful) {
                        Log.d("DEBUG", "Login Success")
                        val user: FirebaseUser? = auth.currentUser
                        if (user != null) {
                            view.showLoginSuccess(user)
                        }
                    } else {
                        Log.d("DEBUG", "Login fail")
                        view.showLoginFailed()
                    }
                }
    }
}

