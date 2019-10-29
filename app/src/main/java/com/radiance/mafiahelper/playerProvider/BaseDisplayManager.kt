package com.radiance.mafiahelper.playerProvider

import com.radiance.mafiahelper.player.Player

open class BaseDisplayManager(val player: Player) {
    val name: String
        get() = player.name
    val statistic: String
        get() = "${player.victoriesCount} / ${player.gameCount}"
}