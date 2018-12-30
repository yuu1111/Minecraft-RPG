package yuu.rpg.spawnblock

import net.minecraft.server.v1_12_R1.*
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import org.bukkit.entity.Player

object SpawnBlock {

    public fun NMSSpawnBlock(p: Player, blockPos: BlockPosition,mobid:String,range:Int,max:Int,mainhanditem: NBTTagCompound, offhanditem: NBTTagCompound) {


        val spawner = (p.world as CraftWorld).handle.getTileEntity(blockPos) as TileEntityMobSpawner?
        val spawnerTag = spawner!!.d()

        spawnerTag.setShort("SpawnRange", range.toShort())
        spawnerTag.setShort("MaxNearbyEntities", max.toShort())

        val spawnData = NBTTagCompound()

        spawnData.setString("id", mobid);
        handitem(spawnData,mainhanditem,offhanditem)

        spawnerTag.set("SpawnData", spawnData)
        spawner.load(spawnerTag);


    }

    fun handitem(spawnData:NBTTagCompound, mainhand:NBTTagCompound, offhand:NBTTagCompound) {

        val handList = NBTTagList()
        handList.add(mainhand);
        handList.add(offhand)
        spawnData.set("HandItems", handList);
    }
}
