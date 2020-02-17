package com.bsvt.storage.playerStorage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {
    @Query("SELECT * FROM playerentity")
    fun getAll(): List<PlayerEntity>

    @Insert
    fun insert(playerEntity: PlayerEntity)

    @Delete
    fun delete(playerEntity: PlayerEntity)
}