package com.bsvt.core

import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.GameOptions

fun GameOptions.getRoleMaxCount(role: Role): Int {
    return when (role) {
        Role.Black -> blackCount
        Role.Doctor -> if (doctorInGame) 1 else 0
        Role.Sheriff -> if (sheriffInGame) 1 else 0
        Role.Red -> redCount
        Role.Unknown -> 0
    }
}

fun ArrayList<Character>.roleCount(role: Role): Int {
    var count = 0
    for (character in this) {
        if (character.role == role)
            count++
    }
    return count
}

fun ArrayList<Character>.clearRole(role: Role, count: Int) {
    var clearCount = 0

    for (character in this) {
        if (character.role == role) {
            if (clearCount == count)
                break

            clearCount++
            character.role = Role.Red
        }
    }
}