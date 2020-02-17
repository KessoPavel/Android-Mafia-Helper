package com.bsvt.core.player.statistics

import java.io.Serializable

data class VictoriesStatistics(
    var victoriesForReds: Int,
    var victoriesForBlacks: Int,
    var victoriesPerSheriff: Int,
    var victoriesPerDoctor: Int
) : Serializable {
    fun getCount() = victoriesForBlacks + victoriesForReds + victoriesPerSheriff + victoriesPerDoctor

    companion object {
        fun getEmpty() = VictoriesStatistics(0, 0, 0, 0)
    }
}