package com.radiance.mafiahelper.view.blackWinners

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.radiance.mafiahelper.R

class BlackWinners : Fragment() {

    companion object {
        fun newInstance() = BlackWinners()
    }

    private lateinit var viewModel: BlackWinnersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.black_winners_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BlackWinnersViewModel::class.java)
    }

}