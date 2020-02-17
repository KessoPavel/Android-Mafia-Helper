package com.bsvt.core.player.statistics

import java.io.Serializable

data class DeathStatistics(
    var firstNightDeaths: Int,
    var firstVotingDeaths: Int,
    var nightDeaths: Int,
    var votingDeaths: Int
): Serializable {
    companion object {
        fun getEmpty() = DeathStatistics(0,0,0,0)
    }
}