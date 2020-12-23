package com.taurin190.androidchat.presentation.login

import com.google.firebase.auth.FirebaseAuth

interface LoginContract {
    interface View {
        fun showLoginFailed()
        fun showLoginSuccess(user: FirebaseAuth)
    }
    interface Presenter {
        fun login(username: String, password: String)
    }
}