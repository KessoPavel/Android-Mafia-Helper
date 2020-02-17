package com.bsvt.core.character

import com.bsvt.core.character.gameStatus.GameStatus
import com.bsvt.core.player.Player
import com.bsvt.core.character.role.Role

class Character (val player: Player) {
    var name: String = ""
    var role: Role = Role.Red
    var status: GameStatus = GameStatus.Active
}