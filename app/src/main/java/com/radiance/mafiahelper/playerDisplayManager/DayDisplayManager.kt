package com.radiance.mafiahelper.playerDisplayManager

import com.radiance.mafiahelper.player.Player

class DayDisplayManager(player: Player,val status: String, val isClickable: Boolean, val number: String): BaseDisplayManager(player) {
    val pseudonym: String
        get() = player.pseudonym
}