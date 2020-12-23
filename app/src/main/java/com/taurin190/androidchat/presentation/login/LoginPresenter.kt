package com.taurin190.androidchat.presentation.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.taurin190.androidchat.util.SchedulerProvider
import java.util.concurrent.Executor

class LoginPresenter(val auth: FirebaseAuth, val view: LoginContract.View, val executor: Executor): LoginContract.Presenter{
    override fun login(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(executor)
                { task ->
                    if (task.isSuccessful) {
                        Log.d("DEBUG", "Login Success")
                    } else {
                        Log.d("DEBUG", "Login fail")
                    }
                }
    }
}

