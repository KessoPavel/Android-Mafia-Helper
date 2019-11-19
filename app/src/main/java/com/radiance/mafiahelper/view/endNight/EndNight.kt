package com.radiance.mafiahelper.view.endNight

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role
import kotlinx.android.synthetic.main.end_night_fragment.*

class EndNight : Fragment() {
    private lateinit var viewModel: EndNightViewModel
    private var game = emptyGame()
    private var player: Player? = null
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
            player = args.deayhPlayer
        }

        viewModel = ViewModelProviders.of(this).get(EndNightViewModel::class.java)
        viewModel.init(game, player, findNavController())

        if (player == null){
            night_resault.text = "Night ended"
        } else {
            night_resault.text = player?.pseudonym + "  died"
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
            viewModel.startDay(direction)
        }
    }

}
