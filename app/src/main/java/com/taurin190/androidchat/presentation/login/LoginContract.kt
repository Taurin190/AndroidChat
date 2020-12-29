package com.taurin190.androidchat.presentation.login

import com.google.firebase.auth.FirebaseUser

interface LoginContract {
    interface View {
        fun showLoginFailed()
        fun showLoginSuccess(user: FirebaseUser)
    }
    interface Presenter {
        fun login(username: String, password: String)
    }
}