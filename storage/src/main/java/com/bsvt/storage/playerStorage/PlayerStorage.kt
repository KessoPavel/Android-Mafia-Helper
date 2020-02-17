package com.bsvt.storage.playerStorage

import com.bsvt.core.player.Player

interface PlayerStorage {
    fun getPlayers(): ArrayList<Player>
    fun savePlayer(player: Player)
    fun deletePlayer(player: Player)
}