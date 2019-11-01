package com.radiance.mafiahelper.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.fragment.BaseFragment
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.PlayerHolder
import kotlin.collections.ArrayList

class Adapter(
    private var players: ArrayList<PlayerHolder>,
    private val listener: HolderListener,
    private val fragment: BaseFragment
) :  RecyclerView.Adapter<BaseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return HolderWrapper.createHolder(parent, listener, fragment)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(players[position])
    }

    fun setData(players: ArrayList<PlayerHolder>){
        this.players = players
    }

    interface HolderListener{
        fun onClick(playerHolder: PlayerHolder)
        fun onLongClick(playerHolder: PlayerHolder, view: View)
        fun playerRoleChanged(playerHolder: PlayerHolder, role: Role)
    }
}