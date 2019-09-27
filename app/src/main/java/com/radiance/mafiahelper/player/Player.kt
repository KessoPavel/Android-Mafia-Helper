package com.radiance.mafiahelper.player

import java.io.Serializable

data class Player (
    var name: String = "",
    var pseudonym: String = "",

    var gamesForReds: Int = 0,
    var gamesForBlacks: Int = 0,
    var gamesPerSheriff: Int = 0,
    var gamesPerDoctor: Int = 0,

    var victoriesForReds: Int = 0,
    var victoriesForBlacks: Int = 0,
    var victoriesPerSheriff: Int = 0,
    var victoriesPerDoctor: Int = 0,

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