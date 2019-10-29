package com.radiance.mafiahelper.gameFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.dialogAddFragment.AddPlayerFragment
import com.radiance.mafiahelper.dialogAddFragment.AddPlayerListener
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.dialogPlayerInfo.PlayerInfoFragment
import com.radiance.mafiahelper.playerListFragment.ListItemListener
import com.radiance.mafiahelper.playerListFragment.PlayerListAdapter
import com.radiance.mafiahelper.playerProvider.BasePlayerProvider
import com.radiance.mafiahelper.playerProvider.PlayerListProvider
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.runOnUiThread

class PlayerListFragment : GameFragment(),
    Adapter.ClickListener, AddPlayerListener {
    override val layoutId: Int = R.layout.fragment_player_list
    private lateinit var players: ArrayList<Player>
    private lateinit var playerProviders: ArrayList<BasePlayerProvider>
    private lateinit var adapter: Adapter
    private val game: Game = Game()

    private val inactiveBackground: Drawable = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure)!!
    private val activeBackground = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure_selected)
    private val activeBackgroundRed = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure_selected_red)
    private val activeBackgroundBlack = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure_selected_black)
    private val activeBackgroundSheriff = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure_selected_detective)
    private val activeBackgroundDoctor = ContextCompat.getDrawable(this.requireContext(), R.drawable.rounded_figure_selected_doctor)
    private val activeIconRed = R.drawable.red
    private val inactiveIconRed = R.drawable.red_gray
    private val activeIconBlack = R.drawable.mafia
    private val inactiveIconBlack = R.drawable.mafia_gray
    private val activeIconSheriff = R.drawable.sheriff
    private val inactiveIconSheriff = R.drawable.sheriff_gray
    private val activeIconDoctor = R.drawable.doctor
    private val inactiveIconDoctor = R.drawable.doctor_gray
    private val emptyIcon = R.drawable.empty

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
        adapter = Adapter(crateProviders(players), this, this)
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

    override fun playerAdded(player: Player) {
        adapter.addPlayer(player)
        adapter.notifyItemInserted(players.size - 1)
        adapter.notifyDataSetChanged()
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

    override fun onClick(player: Player) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun crateProviders(players: ArrayList<Player>): ArrayList<BasePlayerProvider>{
        playerProviders = ArrayList<BasePlayerProvider>()

        for (player in players){
            val inactiveBackground = inactiveBackground
            var activeBackground = activeBackground
            var activeIcon = emptyIcon
            var inactiveIcon = emptyIcon

            if (player.isBestBlack){
                activeBackground = activeBackgroundBlack
                activeIcon = activeIconBlack
                inactiveIcon = inactiveIconBlack
            } else if (player.isBestRed) {
                activeBackground = activeBackgroundRed
                activeIcon = activeIconRed
                inactiveIcon = inactiveIconRed
            } else if (player.isBestDoctor){
                activeBackground = activeBackgroundDoctor
                activeIcon = activeIconDoctor
                inactiveIcon = inactiveIconDoctor
            } else if (player.isBestSheriff) {
                activeBackground = activeBackgroundSheriff
                activeIcon = activeIconSheriff
                inactiveIcon = inactiveIconSheriff
            }

            playerProviders.add(BasePlayerProvider(player = player, inactiveBackground = inactiveBackground, activeBackground = activeBackground, inactiveIcon = inactiveIcon, activeIcon = activeIcon))
        }

        return playerProviders
    }

}