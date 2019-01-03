package yuu.rpg.item

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

import java.util.ArrayList

object ItemUtil {

    fun itemcreate(name: String, type: Material, lore1: String, lore2: String): ItemStack {

        val lores = ArrayList<String>()

        if (lore1.isNotEmpty())
            lores.add(lore1)

        if (lore2.isNotEmpty())
            lores.add(lore2)

        val item = ItemStack(type)
        val im = item.itemMeta
        im.lore = lores
        im.displayName = ChatColor.RESET.toString() + name
        item.itemMeta = im

        return item
    }

    fun addenc(item: ItemStack, enc: Enchantment, enclv: Int, flag: Boolean): ItemStack {

        item.addUnsafeEnchantment(enc, enclv)
        if (flag) {
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        }
        return item
    }
}
