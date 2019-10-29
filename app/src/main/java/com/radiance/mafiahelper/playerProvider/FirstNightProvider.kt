package com.radiance.mafiahelper.playerProvider

import com.radiance.mafiahelper.player.Role

interface FirstNightProvider: PlayerProvider {
    val isRed: Boolean
    val isBlack: Boolean
    val isDoctor: Boolean
    val isSheriff: Boolean
}