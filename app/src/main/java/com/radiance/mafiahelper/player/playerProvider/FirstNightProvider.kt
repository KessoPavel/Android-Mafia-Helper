package com.radiance.mafiahelper.player.playerProvider

interface FirstNightProvider: PlayerProvider {
    val isRed: Boolean
    val isBlack: Boolean
    val isDoctor: Boolean
    val isSheriff: Boolean
}