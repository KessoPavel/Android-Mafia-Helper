package com.radiance.mafiahelper.player

import java.io.Serializable

data class Player (
    var name: String = "",
    var pseudonym: String = "",
    var role: Role = Role.Red,
    var isDeath: Boolean = false,

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

    var gameCount: Int = 0,
    var victoriesCount: Int = 0,
    var isBestRed: Boolean = false,
    var isBestBlack: Boolean = false,
    var isBestDoctor: Boolean = false,
    var isBestSheriff: Boolean = false
    ): Serializable {
    override fun equals(other: Any?): Boolean {
        return name == (other as Player).name
    }
}