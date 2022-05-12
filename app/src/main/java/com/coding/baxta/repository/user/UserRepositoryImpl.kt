package com.coding.baxta.repository.user

import android.content.Context
import com.coding.baxta.exceptions.NoInternetException
import com.coding.baxta.local.user.UserLocalSource
import com.coding.baxta.local.user.UserMapper.mapToEntityUsers
import com.coding.baxta.local.user.entity.User
import com.coding.baxta.network.user.UserNetworkSource
import com.coding.baxta.util.ConnectionUtil.isConnected
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val context: Context,
    private val userLocalSource: UserLocalSource,
    private val userNetworkSource: UserNetworkSource
) : IUserRepository {

    override suspend fun getUsers() {
        return if (context.isConnected()) {
            val newUsers = userNetworkSource.getUsers()
            userLocalSource.insertUsers(newUsers.mapToEntityUsers())
        } else {
            throw NoInternetException()
        }
    }

    override fun listenToUsersList(): Flow<List<User>> {
        return userLocalSource.watchUsersLists()
    }
}