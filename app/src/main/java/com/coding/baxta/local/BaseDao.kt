package com.coding.baxta.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(t: List<T>)

}