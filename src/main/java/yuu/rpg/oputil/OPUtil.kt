package yuu.rpg.oputil

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.inventory.ItemStack
import yuu.rpg.config.CustomConfig
import yuu.rpg.item.ItemUtil
import yuu.rpg.RPG
import yuu.rpg.item.ItemBuilder
import yuu.rpg.item.ItemDB


class OPUtil internal constructor(private val plugin: RPG) : CommandExecutor {


    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val p = sender as Player
        val id = p.uniqueId
        val inv: Inventory

        if (args.isNotEmpty()) {
            when {
                args[0].equals("gui", ignoreCase = true) -> {
                    inv = Bukkit.createInventory(null, 54, "OPGUI_MainMenu")
                    inv.setItem(10, ItemUtil.itemcreate("OPGUI_SpawnBlock1", Material.STONE,"",""))

                    p.openInventory(inv)
                }
        /*        args[0].equals("test", ignoreCase = true) -> {
                    val x = args[1].toDouble()
                    val y = args[2].toDouble()
                    val z = args[3].toDouble()
                    p.sendMessage("X:"+x+"Y:"+y+"Z:"+z)
                    val loc = Location(p.location.world, x, y, z)
                    val zombie2 = yuu.rpg.entity.Zombie(loc)
                    (loc.world as CraftWorld).handle.addEntity(zombie2, CreatureSpawnEvent.SpawnReason.CUSTOM)
                }*/
            }
        }
        return true
    }
}