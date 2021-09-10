package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt()

    fun attack(opponent: Fightable): Int
}

class Player(
    var name: String,
    var isBlessed: Boolean,
    override val powerType: String,
    override var healthPoints: Int,
) : Fightable {

    override fun attack(opponent: Fightable): Int {
        var damagePoints = damageRoll
        if (!isBlessed) {
            damagePoints *= 2
        }
        opponent.healthPoints -= damagePoints
        return damagePoints
    }
}

abstract class Monster(
    open var name: String,
    open var description: String,
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll
        return damageRoll
    }
}

class Goblin(
    name: String,
    description: String,
    override val powerType: String,
    override var healthPoints: Int,
) : Monster(name, description) {
    override val damageRoll: Int
        get() = super.damageRoll / 2
}