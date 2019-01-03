package yuu.rpg.oputil

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG
import yuu.rpg.item.ItemDB
import yuu.rpg.item.ItemUtil

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
        val gui_material: Inventory
        if (name == "OPGUI_MainMenu") {
            when (slot) {

                10 -> {

                    gui_spawnblcok = Bukkit.createInventory(null, 54, "OPGUI_SpawnBlock1")
                    gui_spawnblcok.setItem(0, ItemDB.SpawnBlockSet_Zombie)
                    p.openInventory(gui_spawnblcok)
                    e.isCancelled = true
                }
                12 -> {

                    gui_material = Bukkit.createInventory(null, 54, "OPGUI_Material1")
                    gui_material.setItem(0, ItemDB.Glow_Coal)
                    p.openInventory(gui_material)
                    e.isCancelled = true
                }
            }
        }
    }
}