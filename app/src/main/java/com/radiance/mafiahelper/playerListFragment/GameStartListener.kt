package com.radiance.mafiahelper.playerListFragment

import com.radiance.mafiahelper.game.Game

interface GameStartListener {
    fun gameStarted(game: Game)
}