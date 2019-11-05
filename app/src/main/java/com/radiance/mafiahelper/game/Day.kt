package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player

class Day {
    val votingList: ArrayList<Player> = ArrayList()

    fun addPlayerToVoting(player: Player){
        votingList.add(player)
    }
}