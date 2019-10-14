package com.radiance.mafiahelper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.radiance.mafiahelper.firstNightFragment.FirstNightFragment
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.gameOptionsFragment.GameOptionsFragment
import com.radiance.mafiahelper.gameOptionsFragment.StartGameListener
import com.radiance.mafiahelper.gettingToKnowFrgment.FirstNightStartListener
import com.radiance.mafiahelper.gettingToKnowFrgment.GettingToKnowFragment
import com.radiance.mafiahelper.player.PlayersManager
import com.radiance.mafiahelper.playerListFragment.GoToOptionListener
import com.radiance.mafiahelper.playerListFragment.PlayerListFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), GoToOptionListener, StartGameListener,
    FirstNightStartListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val players = PlayersManager.loadPlayers()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, PlayerListFragment.newInstance(players), PlayerListFragment.TAG)
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

    override fun goToOptions(game: Game) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, GameOptionsFragment.newInstance(game), GameOptionsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun goToGettingToKnow(game: Game) {
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
}
