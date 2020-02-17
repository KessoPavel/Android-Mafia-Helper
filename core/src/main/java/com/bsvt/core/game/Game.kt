package com.bsvt.core.game

import com.bsvt.core.character.Character
import java.io.Serializable

class Game: Serializable {
    var characters: ArrayList<Character> = ArrayList()

    val gameOptions = GameOptions()
}