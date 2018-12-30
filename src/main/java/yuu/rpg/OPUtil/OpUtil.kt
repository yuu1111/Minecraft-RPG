package yuu.rpg.OPUtil

import org.bukkit.Bukkit
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
import yuu.rpg.Config.CustomConfig
import yuu.rpg.Item.ItemUtil
import yuu.rpg.RPG


class OpUtil internal constructor(private val plugin: RPG) : CommandExecutor {


    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val p = sender as Player
        val id = p.uniqueId
        val inv: Inventory


        if (args.isNotEmpty()) {
            when {
                args[0].equals("gui", ignoreCase = true) -> {
                    inv = Bukkit.createInventory(null, 54, "Item")
                    inv.setItem(0, ItemUtil.itemcreate("test", Material.STONE, ""))
                    p.openInventory(inv)
                }

                args[0].equals("spawnblock", ignoreCase = true) -> {

                    val keyitem: ItemStack = ItemUtil.itemcreate("スポーンブロック設置", Material.STICK, args[1])
                    p.inventory.addItem(keyitem)
                }
                args[0].equals("test", ignoreCase = true) -> {
                    val x = args[1].toDouble()
                    val y = args[2].toDouble()
                    val z = args[3].toDouble()
                    p.sendMessage("X:"+x+"Y:"+y+"Z:"+z)
                    val loc = Location(p.location.world, x, y, z)
                    val zombie2 = yuu.rpg.Entity.Zombie(loc)
                    (loc.world as CraftWorld).handle.addEntity(zombie2, CreatureSpawnEvent.SpawnReason.CUSTOM)

                }
                args[0].equals("blockgui", ignoreCase = true) -> {

                    inv = Bukkit.createInventory(null, 54, "OPGUISpawnBlock1")
                    inv.setItem(0, ItemUtil.itemcreate("test", Material.STONE))
                    p.openInventory(inv)

                }
            }
        }
        return true
    }
}