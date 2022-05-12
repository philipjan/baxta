package com.coding.baxta.network.user

import com.coding.baxta.model.user.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserNetworkSource(private val client: HttpClient) {
    suspend fun getUsers(): List<User> {
        return client.get("/baxta_users.json?key=59ead550").body()
    }
}