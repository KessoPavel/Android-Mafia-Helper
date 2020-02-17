package com.radiance.mafiahelper.view.aliasPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import com.radiance.mafiahelper.view.aliasPicker.adapter.AliasAdapter
import kotlinx.android.synthetic.main.alias_picker_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.collections.ArrayList

class AliasPicker : Fragment() {
    lateinit var adapter: AliasAdapter
    private var game: Game = Game()
    private val viewModel: AliasPickerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alias_picker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = AliasPickerArgs.fromBundle(it)
            game = args.game
        }

        viewModel.init(game)

        val dragAndDrop = DragAndDrop(this)
        val touchHelper = ItemTouchHelper(dragAndDrop)
        touchHelper.attachToRecyclerView(ap_recycler)

        adapter = AliasAdapter(
            players = viewModel.players.value?: ArrayList(),
            touchHelper =  touchHelper,
            aliasListener = viewModel)

        ap_recycler.layoutManager = LinearLayoutManager(context)
        ap_recycler.adapter = adapter

        ap_play.setOnClickListener{
            val game = viewModel.createGame()
            val direction = AliasPickerDirections.gotToFirstNight(game)
            findNavController().navigate(direction)
        }

        (activity as AppCompatActivity).setSupportActionBar(gameOptionToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.choose_the_order_of_the_players)

        gameOptionToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun onItemMove(from: Int, to: Int){
        viewModel.changePlayerPosition(from, to)
        adapter.notifyItemMoved(from, to)
    }
}
