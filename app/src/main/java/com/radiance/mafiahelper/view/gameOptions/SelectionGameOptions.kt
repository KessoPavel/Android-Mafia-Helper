package com.radiance.mafiahelper.view.gameOptions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.GameOptions
import com.radiance.mafiahelper.view.playersPicker.PlayersPickerArgs
import com.radiance.mafiahelper.view.playersPicker.PlayersPickerViewModel
import kotlinx.android.synthetic.main.fragment_game_options.*
import kotlinx.android.synthetic.main.game_options_fragment.*

class SelectionGameOptions : Fragment() {
    private lateinit var viewModel: SelectionGameOptionsViewModel

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

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this))
            .get(SelectionGameOptionsViewModel::class.java)

        viewModel.gameOptions.observe(this, Observer { gameOptions -> updateGameOptions(gameOptions) })
        viewModel.init(game = game?: Game(ArrayList()));

        go_number_piker.setOnValueChangedListener { _, _, newVal ->
            viewModel.blackCount(newVal)
        }

        go_doctor_check_box.setOnCheckedChangeListener { _, isChecked ->
            viewModel.doctorCheck()

        }

        go_sheriff_check_box.setOnCheckedChangeListener { _, isChecked ->
            viewModel.sheriffCheck()
        }
    }

    private fun updateGameOptions(gameOptions: GameOptions) {
        go_number_piker.maxValue = gameOptions.playersCount
        go_number_piker.minValue = 1

        go_doctor_check_box.isChecked = gameOptions.doctorInGame
        go_sheriff_check_box.isChecked = gameOptions.sheriffInGame
        go_black_count.text = gameOptions.blackCount.toString()
        go_red_count.text = gameOptions.redCount.toString()
        go_doctor_count.text = if (gameOptions.doctorInGame) "1" else "0"
        go_sheriff_count.text = if (gameOptions.sheriffInGame) "1" else "0"
    }

}
