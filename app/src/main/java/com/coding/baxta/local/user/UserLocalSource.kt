package com.coding.baxta.local.user

import com.coding.baxta.local.user.dao.UserDao
import com.coding.baxta.local.user.entity.User

class UserLocalSource(private val userDao: UserDao) {

    suspend fun insertUsers(users: List<User>) {
        userDao.insert(users)
    }

    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    suspend fun getUsers() =
        userDao.getUserList()

    fun watchUsersLists() =
        userDao.getUserListFlow()

    fun watchUserFlow(userId: String) =
        userDao.getUserInfoFlow(userId)

    suspend fun watchUser(userId: String) =
        userDao.getUserInfo(userId)

}