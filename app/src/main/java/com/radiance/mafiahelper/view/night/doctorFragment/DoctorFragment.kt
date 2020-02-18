package com.radiance.mafiahelper.view.night.doctorFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.bsvt.core.roleCount
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.setUpToolbar
import com.radiance.mafiahelper.view.night.doctorFragment.adapter.DoctorAdapter
import com.radiance.mafiahelper.view.night.doctorFragment.adapter.DoctorItem
import com.radiance.mafiahelper.view.night.night.NightFragmentArgs
import kotlinx.android.synthetic.main.doctor_choise_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class DoctorFragment : Fragment() {
    private val viewModel: DoctorFragmentViewModel by viewModels()
    private val adapter: DoctorAdapter by lazy { DoctorAdapter(ArrayList(), viewModel) }
    private var game = Game()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.doctor_choise_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = NightFragmentArgs.fromBundle(it)
            game = args.game
        }


        if (game.characters.roleCount(Role.Doctor) == 0) {
            dc_doctor_died.setOnClickListener{}
        } else {
            dc_doctor_died.visibility = View.INVISIBLE
        }

        dc_recycler.layoutManager = LinearLayoutManager(context)
        dc_recycler.adapter = adapter

        viewModel.characters.observe(this, Observer { updateCharacters() })

        viewModel.init(game)

        dcf_play.setOnClickListener{
            val direction = DoctorFragmentDirections.endNight(viewModel.createGame())
            findNavController().navigate(direction)
        }

        setUpToolbar(mainToolbar, R.string.doctor_choice)
    }

    private fun updateCharacters() {
        val items = ArrayList<DoctorItem>()

        for (character in viewModel.characters.value?:ArrayList()) {
            items.add(DoctorItem(character, character == viewModel.currentPlayer.value))
        }

        adapter.characters = items
        adapter.notifyDataSetChanged()
    }

}
