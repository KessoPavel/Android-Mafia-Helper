package com.radiance.mafiahelper.view.gameSettings.gameOptions

import com.bsvt.core.game.GameOptions

fun GameOptions.set(options: GameOptions) {
    playersCount = options.playersCount
    blackCount = options.blackCount
    sheriffInGame = options.sheriffInGame
}