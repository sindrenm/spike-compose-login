package com.sindrenm.spikes.compose.login.data.models

import java.util.*

data class User(
    val name: String,
    val emailAddress: String,
    val password: String,
    val accessToken: String = UUID.randomUUID().toString(),
)

val mockUsers = mutableListOf(
    User(
        name = "John Doe",
        emailAddress = "john.doe@example.com",
        password = "gimmethedoe",
    ),
    User(
        name = "G. Root",
        emailAddress = "admin@root.com",
        password = "iamroot",
    ),
)
