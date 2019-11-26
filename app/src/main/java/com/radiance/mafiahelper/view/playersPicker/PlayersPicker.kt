package com.radiance.mafiahelper.view.playersPicker

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.enter
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.out
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.players_picker_fragment.*

class PlayersPicker : Fragment() {
    lateinit var adapter: RecyclerAdapter
    private lateinit var activeBackground: Drawable
    private lateinit var passiveBackground: Drawable
    private lateinit var viewModel: PlayersPickerViewModel

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

        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange()
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    isShow = true;
//                    showOption(R.id.action_info);
//                } else if (isShow) {
//                    isShow = false;
//                    hideOption(R.id.action_info);
//                }
            }

        });
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pick_player_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activeBackground = context?.getDrawable(R.drawable.list_item)!!
        passiveBackground = context?.getDrawable(R.drawable.unselected_list_item)!!

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this))
            .get(PlayersPickerViewModel::class.java)

        viewModel.init(findNavController(), context!!)

        viewModel.players.observe(this, Observer { playersUpdated() })
        viewModel.playersInGame.observe(this, Observer { playersUpdated() })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameIsReady(gameIsReady) })

        adapter = RecyclerAdapter(
            players = convertPlayerToPlayerHolder(),
            holderBuilder = PlayerPickerViewHolderBuilder(viewModel)
        )
        pp_recycler.layoutManager = LinearLayoutManager(context)
        pp_recycler.adapter = adapter

        pp_add_player.setOnClickListener {
            val direction = PlayersPickerDirections.addNewPlayer()
            viewModel.onAddPlayerClick(direction)
        }

        pp_play.setOnClickListener {
            val direction =
                PlayersPickerDirections.actionPlayersPickerToSelectionGameOptions(viewModel.game())
            viewModel.onPlayClick(direction)
        }

        arguments?.let {
            val saveArgs = PlayersPickerArgs.fromBundle(it)
            val player = saveArgs.newPlayer

            viewModel.addPlayer(player)

            it.clear()
        }
    }

    private fun playersUpdated() {
        adapter.setData(convertPlayerToPlayerHolder())
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

    private fun convertPlayerToPlayerHolder(): ArrayList<PlayerHolder> {
        val answer = ArrayList<PlayerHolder>()

        for (player in viewModel.players.value!!) {
            answer.add(
                PlayerHolder(
                    player = player,
                    background = getBackGround(player, viewModel.playersInGame.value!!),
                    icon = getIcon(player),
                    isSelected = viewModel.playersInGame.value!!.contains(player)
                )
            )
        }

        return answer
    }

    private fun getIcon(player: Player): Int {
        return when {
            player.isBestBlack -> R.drawable.mafia_gray
            player.isBestRed -> R.drawable.red_gray
            player.isBestDoctor -> R.drawable.doctor_gray
            player.isBestSheriff -> R.drawable.sheriff_gray
            else -> R.drawable.empty
        }
    }

    private fun getBackGround(player: Player, game: ArrayList<Player>): Drawable {
        return if (game.contains(player)) activeBackground else passiveBackground
    }


    private class PlayerPickerViewHolderBuilder(private val viewModel: PlayersPickerViewModel) :
        HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflationView = parent.inflate(R.layout.player_picker_item, false)
            return PlayerPickerViewHolder(inflationView, viewModel)
        }

    }
}
