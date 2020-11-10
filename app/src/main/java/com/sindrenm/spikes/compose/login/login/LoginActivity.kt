package com.sindrenm.spikes.compose.login.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.sindrenm.spikes.compose.login.profile.ProfileActivity
import com.sindrenm.spikes.compose.login.ui.ComposeLoginScreenSpikeTheme

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeLoginScreenSpikeTheme {
                LoginScreen(
                    viewModel = viewModel,
                    onSignInSuccess = {
                        startActivity(Intent(this, ProfileActivity::class.java))
                        finish()
                    },
                )
            }
        }
    }
}
