package com.radiance.mafiahelper.player

import java.io.Serializable

data class Player (
    val name: String = "",
    val pseudonym: String = "",

    val gamesForReds: Int = 0,
    val gamesForBlacks: Int = 0,
    val gamesPerSheriff: Int = 0,
    val gamesPerDoctor: Int = 0,

    val victoriesForReds: Int = 0,
    val victoriesForBlacks: Int = 0,
    val victoriesPerSheriff: Int = 0,
    val victoriesPerDoctor: Int = 0,

    var firstNightDeaths: Int = 0,
    var firstVotingDeaths: Int = 0,
    var nightDeaths: Int = 0,
    var votingDeaths: Int = 0,

    var statistic: String = "",
    var isBestRed: Boolean = false,
    var isBestBlack: Boolean = false,
    var isBestDoctor: Boolean = false,
    var isBestDetective: Boolean = false
    ): Serializable