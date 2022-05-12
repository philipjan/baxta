package com.coding.baxta.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.baxta.local.user.dao.UserDao
import com.coding.baxta.local.user.entity.User

@Database(entities = [User::class], version = UserDb.DB_VERSION)
abstract class UserDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "user_db"
        const val DB_VERSION = 1
        var INSTANCE: UserDb? = null

        fun create(ctx: Context): UserDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    ctx,
                    UserDb::class.java,
                    DB_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }
}