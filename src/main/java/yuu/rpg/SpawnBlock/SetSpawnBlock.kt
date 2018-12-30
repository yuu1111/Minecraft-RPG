package yuu.rpg.SpawnBlock

import net.minecraft.server.v1_12_R1.SoundEffects.id
import org.bukkit.Material
import org.bukkit.block.Sign
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import yuu.rpg.ItemUtil
import net.minecraft.server.v1_12_R1.NBTTagCompound
import net.minecraft.server.v1_12_R1.NBTTagList
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import net.minecraft.server.v1_12_R1.TileEntityMobSpawner
import net.minecraft.server.v1_12_R1.BlockPosition
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import yuu.rpg.CustomConfig
import yuu.rpg.RPG


class SetSpawnBlock internal constructor(private val plugin: RPG) : Listener {


    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? =  uuid.getConfig()

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val p: Player = e.player
        val handitem: ItemStack = p.inventory.itemInMainHand
        val keyitem: ItemStack = ItemUtil.ItemCreate("スポーンブロック設置",Material.STICK,0,"","")
        if(handitem == keyitem){
            p.sendMessage("テスト")
            val clickedBlock = e.clickedBlock
            val loc = clickedBlock.location

            val blockPos = BlockPosition(loc.blockX, loc.blockY, loc.blockZ)
            val spawner = (p.world as CraftWorld).handle.getTileEntity(blockPos) as TileEntityMobSpawner?
            val spawnerTag = spawner!!.d()
            spawnerTag.setShort("SpawnRange", 20.toShort())
            spawnerTag.setShort("MaxNearbyEntities", 100.toShort())
            val handList = NBTTagList()
            val armorList = NBTTagList()
            val mainHand = NBTTagCompound()
            val offHand = NBTTagCompound()
            mainHand.setString("id", "minecraft:diamond_sword")
            mainHand.setShort("Count", 1.toShort())
            val enchantments = NBTTagList()
            val sharpness3 = NBTTagCompound()
            sharpness3.setShort("id", 16.toShort())
            sharpness3.setShort("lvl", 3.toShort())
            enchantments.add(sharpness3)
            val ench = NBTTagCompound()
            ench.set("ench", enchantments)
            mainHand.set("tag", ench)
            handList.add(mainHand)
            handList.add(offHand)
            val helmet = NBTTagCompound()
            val chestplate = NBTTagCompound()
            val leggings = NBTTagCompound()
            val boots = NBTTagCompound()
            helmet.setString("id", "minecraft:leather_helmet")
            helmet.setShort("Count", 1.toShort())
            chestplate.setString("id", "minecraft:golden_chestplate")
            chestplate.setShort("Count", 1.toShort())
            //we're leaving the leg slot empty
            boots.setString("id", "minecraft:iron_boots")
            boots.setShort("Count", 1.toShort())
            armorList.add(boots)
            armorList.add(leggings)
            armorList.add(chestplate)
            armorList.add(helmet)
            val spawnData = NBTTagCompound()
            spawnData.setString("id", "zombie") //sets the spawner to a zombie
            spawnData.set("HandItems", handList)
            spawnData.set("ArmorItems", armorList)
            spawnerTag.set("SpawnData", spawnData)

            spawner.load(spawnerTag)


        }

    }
}

