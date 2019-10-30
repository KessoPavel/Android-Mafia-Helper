package com.radiance.mafiahelper.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.dialogs.dialogAddFragment.AddPlayerFragment
import com.radiance.mafiahelper.dialogs.dialogAddFragment.AddPlayerListener
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.dialogs.dialogPlayerInfo.PlayerInfoFragment
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.runOnUiThread

class PlayerListFragment : BaseFragment(),
    Adapter.HolderListener, AddPlayerListener {
    override val layoutId: Int = R.layout.fragment_player_list
    protected lateinit var players: ArrayList<Player>
    private lateinit var adapter: Adapter
    private val game: Game = Game()

    private lateinit var inactiveBackground: Drawable
    private lateinit var activeBackground: Drawable
    private lateinit var activeBackgroundRed: Drawable
    private lateinit var activeBackgroundBlack: Drawable
    private lateinit var activeBackgroundSheriff: Drawable
    private lateinit var activeBackgroundDoctor: Drawable
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
        adapter = Adapter(createProviders(players), this, this)
        recyclerView.adapter = adapter
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        inactiveBackground = ContextCompat.getDrawable(context!!, R.drawable.rounded_figure)!!
        activeBackground = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected)!!
        activeBackgroundRed = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_red)!!
        activeBackgroundBlack = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_black)!!
        activeBackgroundSheriff = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_detective)!!
        activeBackgroundDoctor = ContextCompat.getDrawable(context, R.drawable.rounded_figure_selected_doctor)!!
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

    override fun playerAdded(player: Player) {
        players.add(player)
        playerHolders.add(createProvider(player))
        adapter.setData(playerHolders)
        adapter.notifyItemInserted(players.size - 1)
        adapter.notifyDataSetChanged()
    }

    override fun onLongClick(playerHolder: PlayerHolder) {
        context?.runOnUiThread {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.root_layout, PlayerInfoFragment.newInstance(playerHolder.player), "PlayerInfo")
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onClick(playerHolder: PlayerHolder) {
        if (playerHolder.isSelected){
            game.removePlayer(playerHolder.player)
            fpl_select_options.text = "${getString(R.string.select_game_options)} | ${game.playersCont} players"
        } else {
            game.addPlayer(playerHolder.player)
        }

        playerHolder.isSelected = !playerHolder.isSelected
        fpl_select_options.text = "${getString(R.string.select_game_options)} | ${game.playersCont} players"

        adapter.notifyDataSetChanged()
    }

    override fun createProvider(player: Player): PlayerHolder {
        val inactiveBackground = inactiveBackground
        var activeBackground = activeBackground
        var activeIcon = emptyIcon
        var inactiveIcon = emptyIcon

        when {
            player.isBestBlack -> {
                activeBackground = activeBackgroundBlack
                activeIcon = activeIconBlack
                inactiveIcon = inactiveIconBlack
            }
            player.isBestRed -> {
                activeBackground = activeBackgroundRed
                activeIcon = activeIconRed
                inactiveIcon = inactiveIconRed
            }
            player.isBestDoctor -> {
                activeBackground = activeBackgroundDoctor
                activeIcon = activeIconDoctor
                inactiveIcon = inactiveIconDoctor
            }
            player.isBestSheriff -> {
                activeBackground = activeBackgroundSheriff
                activeIcon = activeIconSheriff
                inactiveIcon = inactiveIconSheriff
            }
        }

        return PlayerHolder(
            player = player,
            inactiveBackground = inactiveBackground,
            activeBackground = activeBackground,
            inactiveIcon = inactiveIcon,
            activeIcon = activeIcon
        )
    }
}