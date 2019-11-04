package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.player.Role

abstract class BaseFragment: Fragment(), Adapter.HolderListener {
    abstract val layoutId: Int
    abstract val title: Int
    protected lateinit var playerHolders: ArrayList<PlayerHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        return initUi(view = view, savedInstanceState = savedInstanceState)
    }

    open fun initUi(view: View, savedInstanceState: Bundle?): View {
        return view
    }

    protected open fun createProviders(players: ArrayList<Player>): ArrayList<PlayerHolder>{
        playerHolders = ArrayList()

        for (player in players){
            val holder = createProvider(player)
            if (holder != null)
                playerHolders.add(holder)
        }

        return playerHolders
    }

    protected open fun createProvider(player: Player): PlayerHolder? = PlayerHolder(player = player)

    override fun playerRoleChanged(playerHolder: PlayerHolder, role: Role) {
    }

    override fun onClick(playerHolder: PlayerHolder) {
    }

    override fun onLongClick(playerHolder: PlayerHolder, view: View) {
    }
}