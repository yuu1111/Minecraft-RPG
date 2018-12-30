package yuu.rpg.item


import net.minecraft.server.v1_12_R1.ItemStack
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack

object NMSItem {

    fun NMSItemChange(item: org.bukkit.inventory.ItemStack): ItemStack {
        return CraftItemStack.asNMSCopy(item)
    }
}