package com.radiance.mafiahelper.view.playersPicker

import com.bsvt.core.player.Player
import com.radiance.mafiahelper.view.playersPicker.adapter.PlayerItem

fun PlayerItem.getStat(): String {
    return "${player.gameCount()}/${player.victoriesCount()}"
}

fun Player.gameCount() = this.gameStatistics.gameCount()

fun Player.victoriesCount() = this.victoriesStatistics.getCount()