package yuu.rpg.spawnblock

import net.minecraft.server.v1_12_R1.*
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers
import yuu.rpg.item.ItemBuilder


class SpawnBlockBuilder {

    private val spawnerTag: NBTTagCompound
    private val spawnData = NBTTagCompound()

    constructor(spawner: TileEntity?) {
        spawnerTag = spawner!!.d()
    }

    fun range(range: Int): SpawnBlockBuilder {
        spawnerTag.setShort("SpawnRange", range.toShort())
        return this
    }

    fun maxentities(max: Int): SpawnBlockBuilder {
        spawnerTag.setShort("MaxNearbyEntities", max.toShort())
        return this
    }

    fun mob(mobid: String): SpawnBlockBuilder {
        spawnData.setString("id", mobid)
        return this
    }

    fun set(): SpawnBlockBuilder {
        spawnerTag.set("SpawnData", spawnData)
        return this
    }

    fun tag(): NBTTagCompound {
        return spawnerTag
    }
}