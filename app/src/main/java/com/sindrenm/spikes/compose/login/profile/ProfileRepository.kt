package com.sindrenm.spikes.compose.login.profile

import android.content.SharedPreferences
import com.sindrenm.spikes.compose.login.data.models.LoadProfileResult
import com.sindrenm.spikes.compose.login.data.models.mockUsers
import kotlinx.coroutines.delay

class ProfileRepository(private val sharedPreferences: SharedPreferences) {
    suspend fun loadProfile(): LoadProfileResult {
        delay(500) // simulated latency

        val token = sharedPreferences.getString("access-token", null)

        val user = mockUsers.first { it.accessToken == token }

        return LoadProfileResult.Success(user)
    }

    suspend fun signOut() {
        sharedPreferences.edit()
            .remove("access-token")
            .apply()
    }
}
