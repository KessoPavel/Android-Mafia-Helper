package com.radiance.mafiahelper.adapter

import android.view.ViewGroup
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.gameFragment.GameFragment
import com.radiance.mafiahelper.gameFragment.PlayerListFragment
import com.radiance.mafiahelper.inflate

object HolderWrapper {
    fun createHolder(parent: ViewGroup, listener: Adapter.ClickListener, fragment: GameFragment): BaseHolder {
        if (fragment is PlayerListFragment){
            val inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
            return PlayerListHolder(inflationView, listener)
        } else {
            val inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
            return PlayerListHolder(inflationView, listener)
        }
    }
}