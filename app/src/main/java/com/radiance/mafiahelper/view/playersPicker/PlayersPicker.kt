package com.radiance.mafiahelper.view.playersPicker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import kotlinx.android.synthetic.main.players_picker_fragment.*

class PlayersPicker : Fragment() {

    companion object {
        fun newInstance() = PlayersPicker()
    }

    private lateinit var viewModel: PlayersPickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_picker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayersPickerViewModel::class.java)
        viewModel.init(context?.getDrawable(R.drawable.list_item)!!, context?.getDrawable(R.drawable.unselected_list_item)!!)

        pp_recycler.layoutManager = LinearLayoutManager(context)
        pp_recycler.adapter = viewModel.adapter
    }
}
