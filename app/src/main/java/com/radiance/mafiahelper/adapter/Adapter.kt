package com.radiance.mafiahelper.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.fragment.GameFragment
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlin.collections.ArrayList

class Adapter(
    private var players: ArrayList<BasePlayerProvider>,
    private val listener: HolderListener,
    private val fragment: GameFragment
) :  RecyclerView.Adapter<BaseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return HolderWrapper.createHolder(parent, listener, fragment)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(players[position])
    }

    fun setData(players: ArrayList<BasePlayerProvider>){
        this.players = players
    }

    interface HolderListener{
        fun onClick(basePlayerProvider: BasePlayerProvider)
        fun onLongClick(basePlayerProvider: BasePlayerProvider)
        fun playerRoleChanged(basePlayerProvider: BasePlayerProvider, role: Role)
    }
}