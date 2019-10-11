package com.radiance.mafiahelper.playerListFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.addPlayerFragment.AddPlayerFragment
import com.radiance.mafiahelper.addPlayerFragment.AddPlayerListener
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class PlayerListFragment : Fragment(),  ListItemListener, AddPlayerListener {
    private lateinit var players: ArrayList<Player>
    private lateinit var listener: GameStartListener
    private lateinit var adapter: PlayerListAdapter
    private val game: Game = Game()


    companion object {
        private const val PLAYER_LIST = "PLAYER_LIST"

        fun newInstance(players: ArrayList<Player>): PlayerListFragment{
            val args = Bundle()
            args.putSerializable(PLAYER_LIST, players)
            val fragment = PlayerListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is GameStartListener){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement ListItemListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        players = arguments!!.getSerializable(PLAYER_LIST) as ArrayList<Player>

        val view = inflater.inflate(R.layout.fragment_player_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.fpl_player_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter =  PlayerListAdapter(players, this, context!!)
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

        fpl_start_game.setOnClickListener{_ -> listener.gameStarted(game)}
    }

    override fun onPlayerSelect(player: Player) {
        game.addPlayer(player)
        fpl_start_game.text = "${getString(R.string.start_game)} | ${game.playersCont} players"
    }

    override fun onPlayerUnSelect(player: Player) {
        game.removePlayer(player)
        fpl_start_game.text = "${getString(R.string.start_game)} | ${game.playersCont} players"
    }

    override fun playerAdded(player: Player) {
        players.add(player)
        adapter.updatePlayerList(players)
        adapter.notifyItemInserted(players.size - 1)
        adapter.notifyDataSetChanged()
    }
}