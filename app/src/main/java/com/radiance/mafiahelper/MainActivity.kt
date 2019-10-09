package com.radiance.mafiahelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayersManager
import com.radiance.mafiahelper.playerListFragment.PlayerListFragmentListener
import com.radiance.mafiahelper.playerListFragment.PlayerListFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), PlayerListFragmentListener {
    val game: Game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val players = PlayersManager.loadPlayers()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, PlayerListFragment.newInstance(players), "Player")
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

    override fun onPlayerSelect(player: Player) {
        toast("Player ${player.name} added to game")
        game.addPlayer(player)
        fpl_start_game.text = "${getString(R.string.start_game)} | ${game.playersCont} players"
    }

    override fun onPlayerUnSelect(player: Player) {
        toast("Player ${player.name} removed from thr game")
        game.removePlayer(player)
        fpl_start_game.text = "${getString(R.string.start_game)} | ${game.playersCont} players"
    }

}
