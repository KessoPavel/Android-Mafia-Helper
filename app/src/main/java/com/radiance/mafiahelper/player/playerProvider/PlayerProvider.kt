package com.radiance.mafiahelper.player.playerProvider

import com.radiance.mafiahelper.player.Player

interface PlayerProvider {
    val player: Player
    val name: String
    val statistic: String
    val pseudonym: String
}