package com.radiance.mafiahelper.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.adapter.BaseHolder
import com.radiance.mafiahelper.fragment.BaseFragment
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.PlayerHolder
import kotlin.collections.ArrayList
import java.util.Collections.swap

class RecyclerAdapter(
    private var players: ArrayList<PlayerHolder>,
    private val holderBuilder: HolderBuilder
) :  RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return holderBuilder.build(parent = parent)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(players[position])
    }

    fun setData(players: ArrayList<PlayerHolder>){
        this.players = players
    }
}