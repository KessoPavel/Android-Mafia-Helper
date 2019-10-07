package com.radiance.mafiahelper.playerListFragment

import com.radiance.mafiahelper.player.Player

interface PlayerListFragmentListener {
    fun onPlayerSelect(player: Player)
    fun onPlayerUnSelect(player: Player)
}