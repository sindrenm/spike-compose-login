package com.sindrenm.spikes.compose.login.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.sindrenm.spikes.compose.login.App
import com.sindrenm.spikes.compose.login.login.LoginActivity
import com.sindrenm.spikes.compose.login.ui.ComposeLoginScreenSpikeTheme

class ProfileActivity : AppCompatActivity() {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeLoginScreenSpikeTheme {
                MainScreen(
                    viewModel = viewModel,
                    onSignedOut = {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
