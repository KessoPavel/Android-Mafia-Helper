package com.radiance.mafiahelper.adapter

import android.view.View
import android.view.ViewGroup
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.fragment.*
import com.radiance.mafiahelper.inflate

object HolderWrapper {
    fun createHolder(parent: ViewGroup, listener: Adapter.ClickListener, fragment: GameFragment): BaseHolder {
        val inflationView: View?
        when (fragment) {
            is PlayerListFragment -> {
                inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
                return PlayerListHolder(inflationView, listener)
            }
            is GettingToKnowFragment -> {
                inflationView = parent.inflate(R.layout.fragment_getting_to_known_item, false)
                return GettingToKnownHolder(inflationView, listener)
            }
            is DayFragment -> {
                inflationView = parent.inflate(R.layout.fragment_day_item, false)
                return DayHolder(inflationView, listener)
            }
            is VotingFragment -> {
                inflationView = parent.inflate(R.layout.fragment_voting_item, false)
                return VotingHolder(inflationView, listener)
            }
            else -> {
                inflationView = parent.inflate(R.layout.fragment_player_list_item, false)
                return PlayerListHolder(inflationView, listener)
            }
        }
    }
}