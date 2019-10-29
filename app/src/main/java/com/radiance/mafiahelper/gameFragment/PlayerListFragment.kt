package com.radiance.mafiahelper.gameFragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.dialogAddFragment.AddPlayerFragment
import com.radiance.mafiahelper.dialogAddFragment.AddPlayerListener
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.dialogPlayerInfo.PlayerInfoFragment
import com.radiance.mafiahelper.playerListFragment.ListItemListener
import com.radiance.mafiahelper.playerListFragment.PlayerListAdapter
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.runOnUiThread

class PlayerListFragment : GameFragment(),
    ListItemListener, AddPlayerListener {
    override val layoutId: Int = R.layout.fragment_player_list
    private lateinit var players: ArrayList<Player>
    private lateinit var adapter: PlayerListAdapter
    private val game: Game = Game()

    companion object {
        fun newInstance(players: ArrayList<Player>): PlayerListFragment {
            val fragment = PlayerListFragment()
            fragment.players = players
            return fragment
        }
    }

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        val recyclerView = view.findViewById<RecyclerView>(R.id.fpl_player_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter =
            PlayerListAdapter(players, this, context!!)
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fpl_add_player.setOnClickListener{ _ -> context?.runOnUiThread {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.root_layout, AddPlayerFragment.newInstance(this@PlayerListFragment), "Add Player")
                ?.addToBackStack(null)
                ?.commit()
        } }

        fpl_select_options.setOnClickListener{ _ -> listener.openGameOption(game)}
    }

    override fun onPlayerSelect(player: Player) {
        game.addPlayer(player)
        fpl_select_options.text = "${getString(R.string.select_game_options)} | ${game.playersCont} players"
    }

    override fun onPlayerUnSelect(player: Player) {
        game.removePlayer(player)
        fpl_select_options.text = "${getString(R.string.select_game_options)} | ${game.playersCont} players"
    }

    override fun onLongClick(player: Player) {
        context?.runOnUiThread {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.root_layout, PlayerInfoFragment.newInstance(player), "PlayerInfo")
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun playerAdded(player: Player) {
        adapter.addPlayer(player)
        adapter.notifyItemInserted(players.size - 1)
        adapter.notifyDataSetChanged()
    }
}