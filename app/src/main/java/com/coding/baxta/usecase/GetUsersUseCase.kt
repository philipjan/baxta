package com.coding.baxta.usecase

import com.coding.baxta.local.user.entity.User
import com.coding.baxta.repository.user.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val userRepository: IUserRepository) {
    suspend fun getUsers() {
        userRepository.getUsers()
    }

    fun watchUsersList(): Flow<List<User>> {
        return userRepository.listenToUsersList()
    }
}