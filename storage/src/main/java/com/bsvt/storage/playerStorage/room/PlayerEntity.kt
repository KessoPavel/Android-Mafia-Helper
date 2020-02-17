package com.bsvt.storage.playerStorage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "games_for_reds") val gamesForReds: Int,
    @ColumnInfo(name = "games_for_blacks") val gamesForBlacks: Int,
    @ColumnInfo(name = "games_per_sheriff") val gamesPerSheriff: Int,
    @ColumnInfo(name = "games_per_doctor") val gamesPerDoctor: Int,

    @ColumnInfo(name = "victories_for_reds") val victoriesForReds: Int,
    @ColumnInfo(name = "victories_for_blacks") val victoriesForBlacks: Int,
    @ColumnInfo(name = "victories_per_sheriff") val victoriesPerSheriff: Int,
    @ColumnInfo(name = "victories_per_doctor") val victoriesPerDoctor: Int,

    @ColumnInfo(name = "first_night_deaths") val firstNightDeaths: Int,
    @ColumnInfo(name = "first_voting_deaths") val firstVotingDeaths: Int,
    @ColumnInfo(name = "night_deaths") val nightDeaths: Int,
    @ColumnInfo(name = "voting_deaths") val votingDeaths: Int
)