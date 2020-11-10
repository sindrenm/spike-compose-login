package com.sindrenm.spikes.compose.login.login

import android.content.SharedPreferences
import com.sindrenm.spikes.compose.login.data.models.LoginResult
import com.sindrenm.spikes.compose.login.data.models.mockUsers
import kotlinx.coroutines.delay


class LoginRepository(private val sharedPreferences: SharedPreferences) {
    suspend fun signIn(username: String, password: String): LoginResult {
        delay(500) // simulated latency

        val user = mockUsers.find { it.emailAddress == username && it.password == password }
            ?: return LoginResult.WrongCredentials

        sharedPreferences.edit()
            .putString("access-token", user.accessToken)
            .apply()

        return LoginResult.Success
    }
}
