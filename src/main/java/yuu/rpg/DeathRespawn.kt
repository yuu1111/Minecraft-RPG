package yuu.rpg

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent

import java.util.UUID

class DeathRespawn internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }
    var uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    internal var config: FileConfiguration? = null

    @EventHandler
    fun onPlayerDeathEvent(e: PlayerDeathEvent) {
        val p = e.entity.player
        p.spigot().respawn()
        uuid = CustomConfig(plugin, "UUID.yml")
        config = uuid.getConfig()
        val id = p.uniqueId
        val X = config!!.getInt("UUID.$id.Spawn.x")
        val Y = config!!.getInt("UUID.$id.Spawn.y")
        val Z = config!!.getInt("UUID.$id.Spawn.z")

        val spawnpoint = Location(p.location.world, X.toDouble(), Y.toDouble(), Z.toDouble())
        p.teleport(spawnpoint)
    }

    @EventHandler
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val clickedBlock = e.clickedBlock
        val material = clickedBlock.type
        if (material == Material.STAINED_GLASS) {
            uuid = CustomConfig(plugin, "UUID.yml")
            val p = e.player
            config = uuid.getConfig()
            val id = p.uniqueId

            config!!.set("UUID.$id.Spawn.x", e.clickedBlock.x)
            config!!.set("UUID.$id.Spawn.y", e.clickedBlock.y)
            config!!.set("UUID.$id.Spawn.z", e.clickedBlock.z)
            uuid.saveConfig()
            p.sendMessage("スポーン地点を設定しました")
        }
    }
}
