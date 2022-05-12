package com.coding.baxta.local.user.dao

import androidx.room.Dao
import androidx.room.Query
import com.coding.baxta.local.BaseDao
import com.coding.baxta.local.user.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<User>() {

    @Query("SELECT * FROM tbl_user")
    abstract suspend fun getUserList(): List<User>

    @Query("DELETE FROM tbl_user")
    abstract suspend fun deleteUsers()

    @Query("SELECT * FROM tbl_user WHERE id == :userid")
    abstract suspend fun getUserInfo(userid: String): User

    @Query("SELECT * FROM tbl_user WHERE id == :userid")
    abstract fun getUserInfoFlow(userid: String): Flow<User>

    @Query("SELECT * FROM tbl_user")
    abstract fun getUserListFlow(): Flow<List<User>>

}