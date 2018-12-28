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
                    val x = args[1]
                    val y = args[2]
                    val z = args[3]
                    val w = p.world;
                    val blockPos = BlockPosition(x.toDouble(), y.toDouble(), z.toDouble())
                    val spawner = (w as CraftWorld).handle.getTileEntity(blockPos)
                    val spawnerTag = spawner!!.d()
                    //スポーンする範囲?
                    spawnerTag.setShort("SpawnRange", 20.toShort())
                    //スポーンする最大数?
                    spawnerTag.setShort("MaxNearbyEntities", 5.toShort())
                    val armorList = NBTTagList()
                    val mainHand = NBTTagCompound()
                    val offHand = NBTTagCompound()
                    val item = ItemUtil.ItemCreate("test", Material.STONE, 2, "aaa", "bbb")

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
                    var item2 = ItemStack(Items.STICK)
                    spawnData.set("ArmorItems", armorList)
                    spawnerTag.set("SpawnData", spawnData)

                    spawner.load(spawnerTag);

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