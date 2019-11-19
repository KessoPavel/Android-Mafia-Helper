package com.radiance.mafiahelper.view.redWinners

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable

import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.red_winners_fragment.*

class RedWinners : Fragment() {
    private lateinit var viewModel: RedWinnersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.red_winners_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RedWinnersViewModel::class.java)
    }

}
