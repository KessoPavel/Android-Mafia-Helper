package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player

class Game (){
    private val players: ArrayList<Player> = ArrayList()
    private var isStarted = false

    val playersCont: Int
        get() = players.size

    fun addPlayer(player: Player){
        if (!isStarted){
            if (!players.contains(player))
                players.add(player)
        }
    }

    fun removePlayer(player: Player){
        if (!isStarted){
            players.remove(player)
        }
    }
}