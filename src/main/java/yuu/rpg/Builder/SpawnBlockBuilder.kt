package yuu.rpg.Builder

import net.minecraft.server.v1_12_R1.*
import yuu.rpg.spawnblock.SpawnBlock


class SpawnBlockBuilder {

    private val spawnerTag: NBTTagCompound
    private val spawnData = NBTTagCompound()
    private val handList = NBTTagList()

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

    fun handitem(mainhand: NBTTagCompound, offhand: NBTTagCompound): SpawnBlockBuilder {
        handList.add(mainhand);
        handList.add(offhand)
        spawnData.set("HandItems",handList);
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