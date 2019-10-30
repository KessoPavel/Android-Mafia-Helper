package com.radiance.mafiahelper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.radiance.mafiahelper.fragment.DayFragment
import com.radiance.mafiahelper.fragment.FirstNightFragment
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.GameListener
import com.radiance.mafiahelper.fragment.GameOptionsFragment
import com.radiance.mafiahelper.fragment.GettingToKnowFragment
import com.radiance.mafiahelper.player.PlayersManager
import com.radiance.mafiahelper.fragment.PlayerListFragment
import com.radiance.mafiahelper.fragment.VotingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    GameListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val players = PlayersManager.loadPlayers()

        val playerListFragment = PlayerListFragment.newInstance(players)

        toolbar.title = "Select players"
        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, playerListFragment, playerListFragment.tag)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openGameOption(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, GameOptionsFragment.newInstance(game), GameOptionsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun startGettingToKnown(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, GettingToKnowFragment.newInstance(game), GettingToKnowFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun startFirstNight(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, FirstNightFragment.newInstance(game), FirstNightFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun startDay(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, DayFragment.newInstance(game), null)
            .addToBackStack(null)
            .commit()
    }

    override fun startVoting(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, VotingFragment.newInstance(game), null)
            .addToBackStack(null)
            .commit()
    }

    override fun startNight(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, DayFragment.newInstance(game), null)
            .addToBackStack(null)
            .commit()
    }
}
