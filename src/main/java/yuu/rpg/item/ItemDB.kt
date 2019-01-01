package yuu.rpg.item

import org.bukkit.ChatColor
import org.bukkit.Material

object ItemDB {
    val SpawnBlockSet = ItemBuilder(Material.STICK).amount(1).name(ChatColor.RESET.toString() + "スポーンブロック設置").lores(arrayOf("aaa","aaa")).make()
}