package com.sindrenm.spikes.compose.login.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sindrenm.spikes.compose.login.App
import com.sindrenm.spikes.compose.login.data.models.LoadProfileResult
import com.sindrenm.spikes.compose.login.data.models.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val user: LiveData<User?> get() = _user

    private val _user = MutableLiveData<User?>(null)

    private val repository = ProfileRepository(sharedPreferences = App.sharedPreferences)

    fun loadProfile() {
        viewModelScope.launch {
            when (val result = repository.loadProfile()) {
                is LoadProfileResult.Success -> _user.value = result.user
            }
        }
    }
}
