package com.bsvt.storage.playerStorage

import android.content.Context
import androidx.room.Room
import com.bsvt.core.player.Player
import com.bsvt.storage.playerStorage.room.PlayerDataBase

class RoomPlayerStorage(context: Context): PlayerStorage {
    private val db = Room.databaseBuilder(context, PlayerDataBase::class.java, "players_storage").allowMainThreadQueries().build()

    override fun getPlayers(): ArrayList<Player> {
        val playersEntity = db.playerDao().getAll()
        val answer = ArrayList<Player>()

        for (player in playersEntity) {
            answer.add(player.toPlayer())
        }

        return answer
    }

    override fun savePlayer(player: Player) {
        db.playerDao().insert(player.toEntity())
    }

    override fun deletePlayer(player: Player) {
        db.playerDao().delete(player.toEntity())
    }
}