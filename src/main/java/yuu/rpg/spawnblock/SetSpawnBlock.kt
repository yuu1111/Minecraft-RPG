package yuu.rpg.spawnblock

import net.minecraft.server.v1_12_R1.*
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import yuu.rpg.item.ItemUtil
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import yuu.rpg.config.CustomConfig
import yuu.rpg.item.NMSItem
import yuu.rpg.RPG
import yuu.rpg.item.ItemBuilder
import yuu.rpg.item.ItemDB


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
        if (handitem == ItemDB.SpawnBlockSet) {
            val clickedBlock = e.clickedBlock
            val material = clickedBlock.type
            if (material == Material.MOB_SPAWNER) {

                val loc = clickedBlock.location
                val blockPos = BlockPosition(loc.blockX, loc.blockY, loc.blockZ)
                if (ItemDB.SpawnBlockSet.lore.toString() == "ゾンビ") {
                    val mainHand = NMSItem.NMSItemChange(ItemUtil.itemcreate("テスト", Material.COAL, "", "")).save(NBTTagCompound())
                    val offHand = NBTTagCompound()

                    SpawnBlock.NMSSpawnBlock(p, blockPos, "zombie", 5, 10, mainHand, offHand)

                }
            }
        }
    }
}


