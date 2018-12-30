package yuu.rpg.Actionbar

import org.bukkit.entity.Player

interface Actionbar {

    fun sendActionbar(p: Player, message: String)
}