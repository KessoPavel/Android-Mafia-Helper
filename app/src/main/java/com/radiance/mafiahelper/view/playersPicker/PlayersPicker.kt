package com.radiance.mafiahelper.view.playersPicker

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.player.Player
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.dialogs.dialogPlayerInfo.PlayerInfoFragment
import com.radiance.mafiahelper.enter
import com.radiance.mafiahelper.out
import com.radiance.mafiahelper.showFragment
import com.radiance.mafiahelper.view.playersPicker.adapter.PlayerItem
import com.radiance.mafiahelper.view.playersPicker.adapter.PlayerPickerAdapter
import kotlinx.android.synthetic.main.players_picker_fragment.*

class PlayersPicker : Fragment(), PlayerPickerAdapter.Holder.LongClickListener {
    private val adapter: PlayerPickerAdapter by lazy { PlayerPickerAdapter(ArrayList(), viewModel, this) }
    private val viewModel: PlayersPickerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_picker_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pick_player_menu, menu)

        val search = menu.findItem(R.id.search)
        val actionView = search.actionView as SearchView
        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filter = query?: ""
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter = newText ?: ""
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.init(context!!)

        viewModel.filteredPlayers.observe(this, Observer { playersUpdated() })
        viewModel.playersInGame.observe(this, Observer { playersUpdated() })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameIsReady(gameIsReady) })

        pp_recycler.layoutManager = LinearLayoutManager(context)
        pp_recycler.adapter = adapter

        pp_add_player.setOnClickListener {


            val direction = PlayersPickerDirections.addNewPlayer()
            findNavController().navigate(direction)
        }

        pp_play.out()

        pp_play.setOnClickListener {
            val direction =
                PlayersPickerDirections.actionPlayersPickerToSelectionGameOptions(viewModel.game())
            findNavController().navigate(direction)
        }

        arguments?.let {bundle ->
            val saveArgs = PlayersPickerArgs.fromBundle(bundle)


            saveArgs.playerName.takeUnless { it == "default" }?.let {
                val player = Player.Builder().name(it).build()
                viewModel.addPlayer(player)
            }

            bundle.clear()
        }
    }

    override fun onLongClick(playerItem: PlayerItem) {
        val fragment = PlayerInfoFragment.createFragment(playerItem.player)
        showFragment(R.id.navHostFragment, fragment)
    }

    private fun playersUpdated() {
        adapter.items = convertPlayerToPlayerHolder()
        adapter.notifyDataSetChanged()
    }

    private fun gameIsReady(isReady: Boolean) {
        pp_play.isClickable = isReady

        if (isReady) {
            pp_play.enter()
        } else {
            pp_play.out()
        }
    }

    private fun convertPlayerToPlayerHolder(): ArrayList<PlayerItem> {
        val answer = ArrayList<PlayerItem>()

        for (player in viewModel.filteredPlayers.value!!) {
            answer.add(
                PlayerItem(
                    player = player,
                    selected = viewModel.playersInGame.value!!.contains(player)
                )
            )
        }

        return answer
    }
}
