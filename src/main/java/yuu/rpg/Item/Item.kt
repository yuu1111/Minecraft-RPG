package yuu.rpg.Item

import net.minecraft.server.v1_12_R1.Items
import net.minecraft.server.v1_12_R1.Blocks
import net.minecraft.server.v1_12_R1.ItemStack
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack

public class Item {

    public fun NMSItem(item: org.bukkit.inventory.ItemStack): ItemStack {
        return CraftItemStack.asNMSCopy(item)
    }
}