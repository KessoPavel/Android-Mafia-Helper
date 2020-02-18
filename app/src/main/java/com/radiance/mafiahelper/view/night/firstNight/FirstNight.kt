package com.radiance.mafiahelper.view.night.firstNight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.enter
import com.radiance.mafiahelper.out
import com.radiance.mafiahelper.setUpToolbar
import com.radiance.mafiahelper.view.night.firstNight.adapter.FirstNightAdapter
import kotlinx.android.synthetic.main.first_night_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class FirstNight : Fragment() {
    private var game = Game()
    private val viewModel: FirstNightViewModel by viewModels()
    private val adapter: FirstNightAdapter by lazy { FirstNightAdapter(viewModel.players.value?: ArrayList(), viewModel) }
    private var savedIsReady = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_night_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = FirstNightArgs.fromBundle(it)
            game = args.game
        }

        fn_recycler.layoutManager = LinearLayoutManager(context)
        fn_recycler.adapter = adapter

        viewModel.players.observe(this, Observer { players -> updatePlayers(players) })
        viewModel.gameIsReady.observe(this, Observer { isReady -> gameIsReady(isReady) })
        viewModel.init(game)

        fn_play.setOnClickListener {
            val game = viewModel.createGame()
            val direction = FirstNightDirections.startDay(game)
            findNavController().navigate(direction)
        }

        setUpToolbar(mainToolbar, R.string.select_game_role)
    }

    private fun gameIsReady(isReady: Boolean) {
        fn_play.isClickable = isReady

        if (savedIsReady != isReady) {
            savedIsReady = isReady
            if (isReady) {
                fn_play.enter()
            } else {
                fn_play.out()
            }
        }
    }

    private fun updatePlayers(players: ArrayList<Character>?) {
        players?.let {
            adapter.characters = it
        }
        adapter.notifyDataSetChanged()
    }
}
