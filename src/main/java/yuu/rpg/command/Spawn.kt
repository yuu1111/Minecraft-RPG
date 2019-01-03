package yuu.rpg.command

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG

class Spawn internal constructor(private val plugin: RPG) : CommandExecutor {


    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val p = sender as Player
        val id = p.uniqueId
        val x = config!!.getInt("UUID.$id.Spawn.x").toDouble()
        val y = config.getInt("UUID.$id.Spawn.y").toDouble()
        val z = config.getInt("UUID.$id.Spawn.z").toDouble()

        p.teleport(Location(p.location.world, x, y, z))
        p.sendMessage("スポーンしました")

        return true
    }
}