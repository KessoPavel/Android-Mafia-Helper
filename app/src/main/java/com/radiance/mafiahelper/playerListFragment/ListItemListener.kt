package com.radiance.mafiahelper.playerListFragment

import com.radiance.mafiahelper.player.Player

interface ListItemListener {
    fun onPlayerSelect(player: Player)
    fun onPlayerUnSelect(player: Player)
}