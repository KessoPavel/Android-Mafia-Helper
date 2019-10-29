package com.radiance.mafiahelper.game

interface GameListener {
    fun openGameOption(game: Game)
    fun startGettingToKnown(game: Game)
    fun startFirstNight(game: Game)
    fun startDay(game: Game)
    fun startVoting(game: Game)
    fun startNight(game: Game)
}