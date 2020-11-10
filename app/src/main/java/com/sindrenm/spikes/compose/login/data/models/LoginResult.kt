package com.sindrenm.spikes.compose.login.data.models

sealed class LoginResult {
    object Success : LoginResult()

    object WrongCredentials : LoginResult()

    object UnknownError : LoginResult()
}
