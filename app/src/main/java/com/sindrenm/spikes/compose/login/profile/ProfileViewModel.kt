package com.sindrenm.spikes.compose.login.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sindrenm.spikes.compose.login.App
import com.sindrenm.spikes.compose.login.data.models.LoadProfileResult
import com.sindrenm.spikes.compose.login.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModel : ViewModel() {
    val user: StateFlow<User?> get() = _user

    private val _user = MutableStateFlow<User?>(null)

    private val repository = ProfileRepository(sharedPreferences = App.sharedPreferences)

    fun loadProfile() {
        viewModelScope.launch {
            when (val result = repository.loadProfile()) {
                is LoadProfileResult.Success -> _user.value = result.user
            }
        }
    }
}
