package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player

class Game (){
    val players: ArrayList<Player> = ArrayList()
    private var isStarted = false

    val playersCont: Int
        get() = players.size

    val maxBlackCount: Int
        get() = playersCont / 4

    val minBlackCount: Int
        get() = 1

    var blackCount: Int = 0
    var doctorInGame: Boolean = false
    var sheriffInGame: Boolean = false

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

    fun cleatPseudonym(){
        for (player in players)
            player.pseudonym = ""
    }
}