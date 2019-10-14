package com.radiance.mafiahelper.playerDisplayManager

import com.radiance.mafiahelper.player.Player

class GettingToKnownDisplayManager(player: Player,val number: String): BaseDisplayManager(player){
    var pseudonym = player.pseudonym
}