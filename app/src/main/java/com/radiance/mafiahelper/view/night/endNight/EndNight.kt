package com.radiance.mafiahelper.view.night.endNight

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game

import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.end_night_fragment.*

class EndNight : Fragment() {
    private val viewModel: EndNightViewModel by viewModels()
    private var game = Game()
    private lateinit var direction: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.end_night_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = EndNightArgs.fromBundle(it)
            game = args.game
        }

        viewModel.init(game)

        if (game.mafiaChoice == null){
            night_resault.text = "Night ended"
        } else {
            if (game.mafiaChoice == game.doctorChoice) {
                night_resault.text = game.mafiaChoice?.name + " not died"
            } else {
                night_resault.text = game.mafiaChoice?.name + "  died"
            }
        }

        if (viewModel.gameIsEnded()){
            when (viewModel.winnersTeam()){
                Role.Red -> {
                    direction = EndNightDirections.redWinners(viewModel.createGame())
                }
                Role.Black -> {
                    direction = EndNightDirections.blackWinners(viewModel.createGame())
                }
            }
        } else {
            direction = EndNightDirections.startDay(viewModel.createGame())
        }

        en_play.setOnClickListener{
            viewModel.clearChouce()
            findNavController().navigate(direction)
        }
    }

}
