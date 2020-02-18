package com.radiance.mafiahelper.view.gameSettings.playersPicker.adapter

import com.bsvt.core.player.Player

data class PlayerItem(
    val player: Player,
    var selected: Boolean
) {
}