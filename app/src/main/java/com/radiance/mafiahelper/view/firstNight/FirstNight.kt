package com.radiance.mafiahelper.view.firstNight

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.first_night_fragment.*

class FirstNight : Fragment() {
    private var game = emptyGame()
    private lateinit var viewModel: FirstNightViewModel
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_night_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = FirstNightArgs.fromBundle(it)
            game = args.game
        }

        viewModel =
            ViewModelProvider(this, SavedStateVMFactory(this)).get(FirstNightViewModel::class.java)

        adapter = RecyclerAdapter(
            players = ArrayList(),
            holderBuilder = FirstNightViewHolderBuilder(viewModel)
        )
        fn_recycler.layoutManager = LinearLayoutManager(context)
        fn_recycler.adapter = adapter

        viewModel.players.observe(this, Observer { players -> updatePlayers(players) })
        viewModel.gameIsReady.observe(this, Observer { isReady -> gameIsReady(isReady) })
        viewModel.init(game, findNavController())

        fn_play.setOnClickListener {
            val game = viewModel.createGame()
            val direction = FirstNightDirections.startDay(game)
            viewModel.startDay(direction)
        }
    }

    private var savedIsReady = true
    private fun gameIsReady(isReady: Boolean) {
        fn_play.isClickable = isReady

        if (savedIsReady != isReady) {
            savedIsReady = isReady
            if (isReady) {
                val valueAnimator = ValueAnimator.ofFloat(300f, 0f)
                valueAnimator.addUpdateListener {
                    fn_play.translationX = it.animatedValue as Float
                }
                valueAnimator.interpolator = LinearInterpolator()
                valueAnimator.duration = 1000
                valueAnimator.startDelay = 500
                valueAnimator.start()
            } else {
                val valueAnimator = ValueAnimator.ofFloat(0f, 300f)
                valueAnimator.addUpdateListener {
                    fn_play.translationX = it.animatedValue as Float
                }
                valueAnimator.interpolator = LinearInterpolator()
                valueAnimator.duration = 1000
                valueAnimator.startDelay = 0
                valueAnimator.start()
            }
        }
    }

    private fun updatePlayers(players: ArrayList<Player>?) {
        adapter.setData(createPlayerHolders(players!!))
        adapter.notifyDataSetChanged()
    }

    private fun createPlayerHolders(players: ArrayList<Player>): ArrayList<PlayerHolder> {
        val answer: ArrayList<PlayerHolder> = ArrayList()

        for (player in players) {
            answer.add(
                PlayerHolder(player = player)
            )
        }

        return answer
    }

    private class FirstNightViewHolderBuilder(private val viewModel: FirstNightViewModel) :
        HolderBuilder {
        override fun build(parent: ViewGroup): Holder {
            val inflateView = parent.inflate(R.layout.first_night_item, false)
            return FirstNightViewHolder(inflateView, viewModel)
        }
    }
}
