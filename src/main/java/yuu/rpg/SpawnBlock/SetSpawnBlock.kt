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
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import yuu.rpg.Config.CustomConfig
import yuu.rpg.Item.NMSItem
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
        val keyitem: ItemStack = ItemUtil.itemcreate("スポーンブロック設置", Material.STICK)

        if (handitem == keyitem) {
            val clickedBlock = e.clickedBlock
            val material = clickedBlock.type
            if (material == Material.MOB_SPAWNER) {

                val loc = clickedBlock.location
                val blockPos = BlockPosition(loc.blockX, loc.blockY, loc.blockZ)
                if(keyitem.lore.toString() == "ゾンビ") {
                    val mainHand = NMSItem.NMSItemChange(ItemUtil.itemcreate("テスト", Material.COAL)).save(NBTTagCompound())
                    val offHand = NBTTagCompound()

                    SpawnBlock.NMSSpawnBlock(p, blockPos, "zombie", 5, 10, mainHand, offHand)
                }
            }
        }
    }
}

