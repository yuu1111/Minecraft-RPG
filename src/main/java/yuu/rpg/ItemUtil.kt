package yuu.rpg

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.java.JavaPlugin

import java.util.ArrayList

object ItemUtil {


    fun ItemCreate(name: String, type: Material, loreline: Int, lore1: String, lore2: String): ItemStack {

        val lores = ArrayList<String>()

        if (loreline == 1) {
            lores.add(lore1)

        } else if (loreline == 2) {
            lores.add(lore1)
            lores.add(lore2)
        }

        val item = ItemStack(type)
        val im = item.itemMeta
        im.lore = lores
        im.displayName = ChatColor.RESET.toString() + name
        item.itemMeta = im

        return item
    }

    fun addenc(item: ItemStack, enc: Enchantment, enclv: Int): ItemStack {

        item.addUnsafeEnchantment(enc, enclv)

        return item
    }
}
