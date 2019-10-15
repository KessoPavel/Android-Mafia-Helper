package com.radiance.mafiahelper.playerDisplayManager

import com.radiance.mafiahelper.player.Player

class VotingDisplayManager(player: Player, val count: String): BaseDisplayManager(player) {
    val pseudonym: String = player.pseudonym
}