package com.radiance.mafiahelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.playerInfo.PlayerInfoFragment
import com.radiance.mafiahelper.playerList.PlayerListFragment

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val player1 = Player(
            name = "Long long long name",
            votingDeaths = 44
        )
        val player2 = Player(
            name = "Long long long name 1 ",
            votingDeaths = 44
        )
        val player3 = Player(
            name = "Long long long name 2",
            votingDeaths = 44
        )

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, PlayerListFragment.newInstance(arrayOf(player3, player1, player3, player2, player3)), "Player")
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
