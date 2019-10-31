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

        val players = PlayersManager.loadPlayers()
        val playerListFragment = PlayerListFragment.newInstance(players)

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
        val fragment = GameOptionsFragment.newInstance(game)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }

    override fun startGettingToKnown(game: Game) {
        val fragment = GettingToKnowFragment.newInstance(game)
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }

    override fun startFirstNight(game: Game) {
        val fragment = FirstNightFragment.newInstance(game)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }

    override fun startDay(game: Game) {
        val fragment = DayFragment.newInstance(game)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }

    override fun startVoting(game: Game) {
        val fragment = VotingFragment.newInstance(game)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }

    override fun startNight(game: Game) {
        val fragment = DayFragment.newInstance(game)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.out)
            .replace(R.id.root_layout, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
    }
}
