package yuu.rpg.oputil

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG

class OPGUIClick internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onInventoryClickEvent(e: InventoryClickEvent) {

        val p = e.whoClicked as Player
        val inv = e.inventory
        val name = e.inventory.name
        val slot = e.slot
        val item =  p.inventory.itemInMainHand
        val gui_spawnblcok: Inventory
        if (name == "OPGUI_MainMenu") {
            when (slot) {

                0 -> {

                    gui_spawnblcok = Bukkit.createInventory(null, 54, "OPGUI_SpawnBlock1")
                    p.openInventory(gui_spawnblcok)
                    e.isCancelled = true


                }
            }
        }
    }
}