package com.radiance.mafiahelper.view.firstNight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.radiance.mafiahelper.game.Game

class FirstNightViewModel(private val state: SavedStateHandle) : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController

    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController
    }
}