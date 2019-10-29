package com.radiance.mafiahelper.playerProvider

import com.radiance.mafiahelper.player.Player

class GettingToKnownDisplayManager(player: Player,val number: String): BaseDisplayManager(player){
    var pseudonym = player.pseudonym
}