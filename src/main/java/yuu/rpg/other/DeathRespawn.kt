package yuu.rpg.other

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG

class DeathRespawn internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    @EventHandler
    fun onPlayerDeathEvent(e: PlayerDeathEvent) {
        val p = e.entity.player
        p.spigot().respawn()

        val id = p.uniqueId
        val x = config!!.getInt("UUID.$id.Spawn.x").toDouble()
        val y = config.getInt("UUID.$id.Spawn.y").toDouble()
        val z = config.getInt("UUID.$id.Spawn.z").toDouble()

        p.teleport(Location(p.location.world, x, y, z))
    }

    @EventHandler
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val clickedBlock = e.clickedBlock
        val material = clickedBlock.type
        if (material == Material.STAINED_GLASS) {
            val p = e.player
            val id = p.uniqueId

            config!!.set("UUID.$id.Spawn.x", e.clickedBlock.x)
            config.set("UUID.$id.Spawn.y", e.clickedBlock.y)
            config.set("UUID.$id.Spawn.z", e.clickedBlock.z)
            uuid.saveConfig()
            p.sendMessage("スポーン地点を設定しました")
        }
    }
}
