package com.bsvt.core.player.statistics

import java.io.Serializable

data class GameStatistics(
    var gamesForReds: Int,
    var gamesForBlacks: Int,
    var gamesPerSheriff: Int,
    var gamesPerDoctor: Int): Serializable {

    fun gameCount() = gamesForReds + gamesForBlacks + gamesPerSheriff + gamesPerDoctor

    companion object {
        fun getEmpty() = GameStatistics(0,0,0,0)
    }
}
