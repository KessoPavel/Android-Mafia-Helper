package com.bsvt.core.game

import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.clearRole
import com.bsvt.core.getRoleMaxCount
import com.bsvt.core.roleCount
import java.io.Serializable

class Game: Serializable {
    var characters: ArrayList<Character> = ArrayList()
    val deathCharacters: ArrayList<Character> = ArrayList()

    val gameOptions = GameOptions()

    fun setRole(role: Role, character: Character) {
        val maxRoleCount = gameOptions.getRoleMaxCount(role)

        if (maxRoleCount == 0)
            return

        val currentRoleCount = characters.roleCount(role)

        if (maxRoleCount <= currentRoleCount) {
            val deletedCount = currentRoleCount - maxRoleCount
            characters.clearRole(role, deletedCount + 1)
        }

        character.role = role
    }

    fun checkRole(): Boolean {
        val blackReady = gameOptions.blackCount == characters.roleCount(Role.Black)
        val sheriffReady = if (gameOptions.sheriffInGame) characters.roleCount(Role.Sheriff) == 1 else true
        val doctorReady = if (gameOptions.doctorInGame) characters.roleCount(Role.Doctor) == 1 else true
        return blackReady && sheriffReady && doctorReady
    }

    val dayList = ArrayList<Day>()
    val votingList = ArrayList<Voting>()

    var day: Day? = null
        set(value) {
            value?.let {
                dayList.add(it)
            }
            field = value
        }

    var voting: Voting? = null
        set(value) {
            value?.let {
                votingList.add(value)
            }
            field = value
        }

}