package com.coding.baxta.repository.user

import com.coding.baxta.local.user.entity.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUsers()
    fun listenToUsersList(): Flow<List<User>>
    fun watchUserFlow(userId: String): Flow<User>
    suspend fun watchUser(userId: String): User
}