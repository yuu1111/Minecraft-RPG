package yuu.rpg

import net.minecraft.server.v1_12_R1.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.craftbukkit.v1_12_R1.CraftServer
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import java.lang.reflect.Array.setShort
import java.lang.reflect.Array.setShort
import java.lang.reflect.Array.setShort
import java.lang.reflect.Array.setShort
import net.minecraft.server.v1_12_R1.Enchantment.enchantments
import org.bukkit.entity.Zombie
import org.bukkit.event.entity.CreatureSpawnEvent
import org.junit.internal.matchers.Each.each
import java.lang.reflect.Array.setShort
import io.reactivex.annotations.SchedulerSupport.CUSTOM
import net.minecraft.server.v1_12_R1.NBTTagCompound
import net.minecraft.server.v1_12_R1.Items
import net.minecraft.server.v1_12_R1.NBTTagList
import net.minecraft.server.v1_12_R1.TileEntityMobSpawner
import net.minecraft.server.v1_12_R1.BlockPosition
import org.bukkit.inventory.ItemStack


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
                    inv.setItem(0, ItemUtil.ItemCreate("test", Material.STONE, 2, "aaa", "bbb"))
                    p.openInventory(inv)
                }

                args[0].equals("spawnblock", ignoreCase = true) -> {
                    val keyitem: ItemStack = ItemUtil.ItemCreate("スポーンブロック設置",Material.STICK,0,"","")
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
            }
        }
        return true
    }
}