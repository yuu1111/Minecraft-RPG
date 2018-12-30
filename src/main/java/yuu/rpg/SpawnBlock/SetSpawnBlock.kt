package yuu.rpg.SpawnBlock

import net.minecraft.server.v1_12_R1.*
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import yuu.rpg.Item.ItemUtil
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import yuu.rpg.CustomConfig
import yuu.rpg.RPG


class SetSpawnBlock internal constructor(private val plugin: RPG) : Listener {


    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val p: Player = e.player
        val handitem: ItemStack = p.inventory.itemInMainHand
        val keyitem: ItemStack = ItemUtil.itemcreate("スポーンブロック設置", Material.STICK, 0, "", "")
        if (handitem == keyitem) {
            val clickedBlock = e.clickedBlock
            val material = clickedBlock.type
            if (material == Material.MOB_SPAWNER) {

                val loc = clickedBlock.location
                val blockPos = BlockPosition(loc.blockX, loc.blockY, loc.blockZ)

                val spawner = (p.world as CraftWorld).handle.getTileEntity(blockPos) as TileEntityMobSpawner?
                val spawnerTag = spawner!!.d()

                spawnerTag.setShort("SpawnRange", 5.toShort())
                spawnerTag.setShort("MaxNearbyEntities", 10.toShort())

                val handList = NBTTagList()

                val offHand = NBTTagCompound()
                val mainHand = ItemStack(Items.SADDLE).save(NBTTagCompound())

                handList.add(mainHand);
                handList.add(offHand)


                val spawnData = NBTTagCompound()
                spawnData.setString("id", "zombie");
                spawnData.set("HandItems", handList);

                spawnerTag.set("SpawnData", spawnData)
                spawner.load(spawnerTag);



            }
        }
    }
}


