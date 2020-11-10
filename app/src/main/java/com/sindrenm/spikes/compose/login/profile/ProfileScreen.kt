package com.sindrenm.spikes.compose.login.profile

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    viewModel: ProfileViewModel,
    onSignedOut: () -> Unit,
) {
    viewModel.loadProfile()

    val userOrNull by viewModel.user.observeAsState()
    val user = userOrNull ?: return

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(user.name, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.onBackground)
            Button(modifier = Modifier.padding(top = 16.dp), onClick = onSignedOut) {
                Text("Sign out")
            }
        }
    }
}
