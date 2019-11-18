package com.radiance.mafiahelper.view.voting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.radiance.mafiahelper.R

class Voting : Fragment() {

    companion object {
        fun newInstance() = Voting()
    }

    private lateinit var viewModel: VotingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.voting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VotingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
