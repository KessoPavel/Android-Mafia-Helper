package com.bsvt.storage

import android.content.Context
import com.bsvt.storage.playerStorage.PlayerStorage
import com.bsvt.storage.playerStorage.RoomPlayerStorage

class AppStorage {
    fun getPlayerStorage(context: Context): PlayerStorage = RoomPlayerStorage(context)
}