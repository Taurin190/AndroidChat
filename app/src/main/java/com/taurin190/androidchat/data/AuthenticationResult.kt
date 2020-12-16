package com.taurin190.androidchat.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class AuthenticationResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : AuthenticationResult<T>()
    data class Error(val exception: Exception) : AuthenticationResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}