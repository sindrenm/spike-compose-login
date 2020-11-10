package com.sindrenm.spikes.compose.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sindrenm.spikes.compose.login.App
import com.sindrenm.spikes.compose.login.data.models.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository(sharedPreferences = App.sharedPreferences)

    val username: LiveData<String> get() = _username
    val password: LiveData<String> get() = _password
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _username = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _errorMessage = MutableLiveData<String?>()

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun clear() {
        _username.value = ""
        _password.value = ""
        _errorMessage.value = ""
    }

    fun signIn(onSuccess: () -> Unit) {
        val username = username.value ?: return
        val password = password.value ?: return

        viewModelScope.launch {
            when (repository.signIn(username, password)) {
                LoginResult.Success -> onSuccess()
                LoginResult.WrongCredentials -> _errorMessage.value = "Wrong username or password."
                LoginResult.UnknownError -> _errorMessage.value = "Something went wrong."
            }
        }
    }
}
