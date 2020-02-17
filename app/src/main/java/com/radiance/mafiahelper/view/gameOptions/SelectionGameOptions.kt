package com.radiance.mafiahelper.view.gameOptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bsvt.core.game.Game
import com.bsvt.core.game.GameOptions
import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.game_options_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class SelectionGameOptions : Fragment() {
    private val viewModel: SelectionGameOptionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_options_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var game: Game? = null

        arguments?.let {
            val saveArgs = SelectionGameOptionsArgs.fromBundle(it)
            game = saveArgs.game
        }

        viewModel.gameOptions.observe(
            this,
            Observer { gameOptions -> updateGameOptions(gameOptions) })
        viewModel.init(game ?: Game())

        go_number_piker.setOnValueChangedListener { _, _, newVal ->
            viewModel.blackCount(newVal)
        }

        go_doctor_check_box.setOnCheckedChangeListener { _, _ ->
            viewModel.doctorCheck()

        }

        go_sheriff_check_box.setOnCheckedChangeListener { _, _ ->
            viewModel.sheriffCheck()
        }

        go_play.setOnClickListener {
            val direction =
                SelectionGameOptionsDirections.goToAliacePicker(viewModel.createGame())
            findNavController().navigate(direction)
        }

        (activity as AppCompatActivity).setSupportActionBar(gameOptionToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.select_game_options)

        gameOptionToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateGameOptions(gameOptions: GameOptions) {
        go_number_piker.maxValue = gameOptions.playersCount / 3
        go_number_piker.minValue = 1

        go_doctor_check_box.isChecked = gameOptions.doctorInGame
        go_sheriff_check_box.isChecked = gameOptions.sheriffInGame
        go_black_count.text = gameOptions.blackCount.toString()
        go_red_count.text = gameOptions.redCount.toString()
        go_doctor_count.text = if (gameOptions.doctorInGame) "1" else "0"
        go_sheriff_count.text = if (gameOptions.sheriffInGame) "1" else "0"
    }

}