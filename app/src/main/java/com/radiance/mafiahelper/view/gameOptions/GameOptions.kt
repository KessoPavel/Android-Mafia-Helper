package com.radiance.mafiahelper.view.gameOptions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.radiance.mafiahelper.R

class GameOptions : Fragment() {

    companion object {
        fun newInstance() = GameOptions()
    }

    private lateinit var viewModel: GameOptionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_options_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOptionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
