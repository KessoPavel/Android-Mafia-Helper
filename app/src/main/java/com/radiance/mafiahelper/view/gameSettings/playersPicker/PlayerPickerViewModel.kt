package com.radiance.mafiahelper.view.gameSettings.playersPicker

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.bsvt.core.player.Player
import com.bsvt.storage.AppStorage
import com.bsvt.storage.playerStorage.PlayerStorage
import com.radiance.mafiahelper.view.gameSettings.playersPicker.adapter.PlayerItem
import com.radiance.mafiahelper.view.gameSettings.playersPicker.adapter.PlayerPickerAdapter

class PlayerPickerViewModel(private val state : SavedStateHandle) : ViewModel(), PlayerPickerAdapter.Holder.ClickListener {
    var filter: String = ""
        set(value) {
            filteredPlayers.value = ArrayList(savedPlayers.filter {
                it.name.contains(value, ignoreCase = true)
            })
        }

    var filteredPlayers: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInGame: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var playerStorage: PlayerStorage
    private var savedPlayers = ArrayList<Player>()

    private val PlayersInGame = "PlayersInGame"

    init {
        playersInGame.value = state.get<ArrayList<Player>>(PlayersInGame)?: ArrayList()
    }

    override fun onClick(playerItem: PlayerItem) {
        playersInGame.value?.let { inGames ->
            if (inGames.contains(playerItem.player)) {
                inGames.remove(playerItem.player)
            } else {
                inGames.add(playerItem.player)
            }
        }
        playersInGame.value = playersInGame.value
        state.set(PlayersInGame, playersInGame.value)

        if (gameIsReady.value != (playersInGame.value?.size!! >= minPlayersCount)) {
            gameIsReady.value = playersInGame.value?.size!! >= minPlayersCount
        }
    }

    fun init(context: Context){
        playerStorage = AppStorage().getPlayerStorage(context)

        savedPlayers = playerStorage.getPlayers()
        filteredPlayers.value = savedPlayers
        gameIsReady.value = playersInGame.value?.size!! >= minPlayersCount
    }

    fun game(): Game {
        return Game().apply { characters = playersToCharacters(playersInGame.value) }
    }

    fun addPlayer(player: Player?) {
        player?.let {
            playerStorage.savePlayer(it)
            filteredPlayers.value = playerStorage.getPlayers()
        }
    }

    private fun playersToCharacters(players: ArrayList<Player>?): ArrayList<Character> {
        val answer = ArrayList<Character>()

        for (player in players?:ArrayList()) {
            answer.add(Character(player))
        }

        return answer
    }

    companion object {
        const val minPlayersCount: Int = 5
    }
}