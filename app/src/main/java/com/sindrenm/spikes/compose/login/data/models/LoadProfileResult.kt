package com.sindrenm.spikes.compose.login.data.models

sealed class LoadProfileResult {
    data class Success(val user: User) : LoadProfileResult()
}
