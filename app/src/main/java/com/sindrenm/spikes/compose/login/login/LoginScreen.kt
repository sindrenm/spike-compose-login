package com.sindrenm.spikes.compose.login.login

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sindrenm.spikes.compose.login.data.models.mockUsers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSignInSuccess: () -> Unit,
) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                for (user in mockUsers) {
                    Chip(
                        text = user.name,
                        onClick = {
                            viewModel.setUsername(user.emailAddress)
                            viewModel.setPassword(user.password)
                        }
                    )
                }
            }

            TextField(
                modifier = Modifier.padding(top = 32.dp).fillMaxWidth(),
                value = username,
                onValueChange = { viewModel.setUsername(it) },
                placeholder = { Text("Username") },
            )

            TextField(
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { viewModel.setPassword(it) },
                placeholder = { Text("Password") },
            )

            errorMessage?.let { errorMessage ->
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = errorMessage,
                    color = MaterialTheme.colors.error,
                )
            }

            Row(
                modifier = Modifier.padding(top = 32.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        viewModel.clear()
                    }
                ) {
                    Text("Clear fields")
                }

                Button(
                    modifier = Modifier.padding(start = 8.dp),
                    enabled = username.isNotBlank() && password.isNotEmpty(),
                    onClick = { viewModel.signIn(onSignInSuccess) },
                ) {
                    Text("Sign in")
                }
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = MaterialTheme.colors.primary,
    foregroundColor: Color = MaterialTheme.colors.onPrimary,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier then Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            text = text,
            style = MaterialTheme.typography.caption,
            color = foregroundColor
        )
    }
}
