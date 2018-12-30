package yuu.rpg.OPUtil

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import yuu.rpg.Config.CustomConfig
import yuu.rpg.RPG

class OPGUIClick internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    fun onInventoryClickEvent(e: InventoryClickEvent) {

        val p = e.whoClicked as Player
        val inv = e.inventory
        val name = inv.name
        val slot = e.slot
        val item =  p.inventory.itemInMainHand
        if (name == "OPGUISpawnBlock1") {
            e.isCancelled
            when (slot) {

                0 -> {
                    p.inventory.remove(item)
                    item.lore!!.set(0,"ゾンビ")
                    p.inventory.addItem(item)
                }
            }
        }
    }
}