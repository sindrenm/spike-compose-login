package com.sindrenm.spikes.compose.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sindrenm.spikes.compose.login.App
import com.sindrenm.spikes.compose.login.data.models.LoginResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModel : ViewModel() {
    private val repository = LoginRepository(sharedPreferences = App.sharedPreferences)

    val username: StateFlow<String> get() = _username
    val password: StateFlow<String> get() = _password
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _errorMessage = MutableStateFlow<String?>(null)

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun clear() {
        _username.value = ""
        _password.value = ""
        _errorMessage.value = null
    }

    fun signIn(onSuccess: () -> Unit) {
        val username = username.value
        val password = password.value

        viewModelScope.launch {
            when (repository.signIn(username, password)) {
                LoginResult.Success -> onSuccess()
                LoginResult.WrongCredentials -> _errorMessage.value = "Wrong username or password."
                LoginResult.UnknownError -> _errorMessage.value = "Something went wrong."
            }
        }
    }
}
