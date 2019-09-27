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
            name = "Паша",
            statistic = "4/5",
            isBestBlack = true
        )
        val player2 = Player(
            name = "Коля",
            statistic = "7/13",
            isBestRed = true
        )
        val player3 = Player(
            name = "Радион",
            statistic = "6/3",
            isBestDoctor = true
        )

        supportFragmentManager
            .beginTransaction()
            .add(R.id.root_layout, PlayerListFragment.newInstance(arrayOf(player1, player3, player2)), "Player")
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
