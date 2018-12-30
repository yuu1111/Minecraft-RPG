package yuu.rpg.Item

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.java.JavaPlugin

import java.util.ArrayList

object ItemUtil {

    fun itemcreate(name: String, type: Material,vararg lore: String): ItemStack {

        val lores = ArrayList<String>()

        if(!lore[0].isEmpty())
            lores.add(lore[0])

        if(!lore[1].isEmpty())
            lores.add(lore[1])

        if(!lore[2].isEmpty())
            lores.add(lore[2])

        val item = ItemStack(type)
        val im = item.itemMeta
        im.lore = lores
        im.displayName = ChatColor.RESET.toString() + name
        item.itemMeta = im

        return item
    }

    fun addenc(item: ItemStack, enc: Enchantment, enclv: Int,flag:Boolean): ItemStack {

        item.addUnsafeEnchantment(enc, enclv)
        if(flag) {
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        }
        return item
    }

}
