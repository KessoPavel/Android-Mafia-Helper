package com.radiance.mafiahelper.firstNightFragment

import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

interface PlayerRoleChangeListener {
    fun playerRoleChanged(player: Player, role: Role)
}
