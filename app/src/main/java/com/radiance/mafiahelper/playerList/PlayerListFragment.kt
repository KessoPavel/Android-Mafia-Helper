package com.radiance.mafiahelper.playerList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.player.Player

class PlayerListFragment : Fragment() {
    private lateinit var players: Array<Player>

    companion object {
        private const val PLAYER_LIST = "PLAYER_LIST"

        fun newInstance(players: Array<Player>): PlayerListFragment{
            val args = Bundle()
            args.putSerializable(PLAYER_LIST, players)
            val fragment = PlayerListFragment()
            fragment.arguments = args
            return fragment
        }
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        players = arguments!!.getSerializable(PLAYER_LIST) as Array<Player>
//
//
//    }
}