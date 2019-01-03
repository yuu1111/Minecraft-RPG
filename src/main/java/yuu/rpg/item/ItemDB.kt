package yuu.rpg.item

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import yuu.rpg.Builder.ItemBuilder


val AQUA = ChatColor.AQUA
val BLACK = ChatColor.BLACK
val BLUE = ChatColor.BLUE
val DARK_AQUA = ChatColor.DARK_AQUA
val DARK_BLUE = ChatColor.DARK_BLUE
val DARK_GRAY = ChatColor.DARK_GRAY
val DARK_GREEN = ChatColor.DARK_GREEN
val DARK_PURPLE = ChatColor.DARK_PURPLE
val DARK_RED = ChatColor.DARK_RED
val GOLD = ChatColor.GOLD
val GRAY = ChatColor.GRAY
val GREEN = ChatColor.GREEN
val LIGHT_PURPLE = ChatColor.LIGHT_PURPLE
val RED = ChatColor.RED
val WHITE = ChatColor.WHITE
val YELLOW = ChatColor.YELLOW

val RESET = ChatColor.RESET.toString()
val BOLD = ChatColor.BOLD.toString()
val ITALIC = ChatColor.ITALIC.toString()
val MAGIC = ChatColor.MAGIC.toString()
val STRIKETHROUGH = ChatColor.STRIKETHROUGH.toString()
val UNDERLINE = ChatColor.UNDERLINE.toString()

object ItemDB {

    val SpawnBlockSet_Blank = ItemBuilder(Material.STICK).amount(1).name(RESET + "スポーンブロック設置").make()
    val SpawnBlockSet_Zombie = ItemBuilder(SpawnBlockSet_Blank).lore(RESET + "ゾンビ").make()

    val Glow_Coal = ItemBuilder(Material.COAL).amount(1).name("光る石炭").lore("何故か光っている石炭").additemflag(ItemFlag.HIDE_ENCHANTS).make()

    val GUI_SpawnBlock = ItemBuilder(Material.MOB_SPAWNER).amount(1).name("OPGUI_SpawnBlock1").make()
    val GUI_Material = ItemBuilder(Material.COAL).amount(1).name("OPGUI_Material1").make()

}