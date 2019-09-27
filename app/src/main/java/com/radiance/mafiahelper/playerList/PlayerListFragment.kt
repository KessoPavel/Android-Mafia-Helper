package com.radiance.mafiahelper.playerList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        players = arguments!!.getSerializable(PLAYER_LIST) as Array<Player>

        val view = inflater.inflate(R.layout.fragment_player_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.fpl_player_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlayerListAdapter(players, context!!)
        return view
    }
}