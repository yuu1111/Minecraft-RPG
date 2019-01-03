package yuu.rpg.command

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG

import java.util.UUID

class JobCommand internal constructor(private val plugin: RPG) : CommandExecutor {

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val p = sender as Player
        val id = p.uniqueId

        if (args.isNotEmpty()) {
            when {
                args[0].equals("check", ignoreCase = true) -> {
                    p.sendMessage("経験値:" + p.exp.toString())
                    p.sendMessage("レベル:" + p.level.toString())
                    p.sendMessage(config!!.getString("UUID.$id.Job"))
                }
            }
        }
        return true
    }
}


