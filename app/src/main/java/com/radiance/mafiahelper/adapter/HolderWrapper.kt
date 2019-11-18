package com.radiance.mafiahelper.adapter

import android.view.View
import android.view.ViewGroup
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.fragment.*
import com.radiance.mafiahelper.inflate

object HolderWrapper {
    fun createHolder(parent: ViewGroup, listener: Adapter.HolderListener, fragment: BaseFragment): BaseHolder {
        val inflationView: View?
        when (fragment) {
            is VotingFragment -> {
                inflationView = parent.inflate(R.layout.fragment_voting_item, false)
                return VotingHolder(inflationView, listener)
            }
            else -> {
                inflationView = parent.inflate(R.layout.fragment_voting_item, false)
                return VotingHolder(inflationView, listener)
            }
        }
    }
}