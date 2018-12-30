package yuu.rpg.actionbar

import org.bukkit.entity.Player

interface Actionbar {

    fun sendActionbar(p: Player, message: String)
}