package com.radiance.mafiahelper.view.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.player.PlayerHolder

class RecyclerAdapter(
    private var players: ArrayList<PlayerHolder>,
    private val holderBuilder: HolderBuilder
) :  RecyclerView.Adapter<Holder>(){

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