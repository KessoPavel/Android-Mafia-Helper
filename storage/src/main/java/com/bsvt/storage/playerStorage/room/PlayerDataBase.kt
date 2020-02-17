package com.bsvt.storage.playerStorage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PlayerEntity::class), version = 1)
abstract class PlayerDataBase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}