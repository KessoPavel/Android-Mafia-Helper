package com.radiance.mafiahelper.playerDisplayManager

import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

class FirstNightDisplayManager(player: Player): BaseDisplayManager(player) {
    val pseudonym: String
        get() = player.pseudonym

    val isRed: Boolean
        get() = player.role == Role.Red

    val isBlack: Boolean
        get() = player.role == Role.Black

    val isDoctor: Boolean
        get() = player.role == Role.Doctor

    val isSheriff: Boolean
        get() = player.role == Role.Sheriff
}