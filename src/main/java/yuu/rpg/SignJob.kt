package yuu.rpg

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.Sign
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

import java.util.UUID


class SignJob internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private var id: UUID? = null
    private var job: String? = null
    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val clickedBlock = e.clickedBlock
        val material = clickedBlock.type
        if (material == Material.SIGN || material == Material.WALL_SIGN) {
            val sign = clickedBlock.state as Sign
            val line1 = sign.getLine(0)
            val p: Player = e.player
            id = p.uniqueId
            job = config!!.getString("UUID.$id.Job")

            jobchange(line1, p)
        }
    }

    private fun jobchange(str: String, p: Player) {

        ymlcheck(job)
        config!!.set("UUID.$id.Lv.$job.Lv", p.level)
        config.set("UUID.$id.Lv.$job.Exp", p.exp)

        val job2: String = JobUtil.jobjptoen(str)
        if (!job2.isEmpty()) {
            p.level = config.getInt("UUID.$id.Lv.$job2.Lv")
            p.exp = config.getDouble("UUID.$id.Lv.$job2.Exp").toFloat()

            config.set("UUID.$id.Job", job2)
            p.sendMessage(str + "になりました")
        }
        uuid.saveConfig()
    }

    private fun ymlcheck(str: String?) {

        if (config!!.getString("UUID.$id.Lv.$str.Lv") == null)
            config.set("UUID.$id.Lv.$str.Lv", 0)

        if (config.getString("UUID.$id.Lv.$str.Exp") == null)
            config.set("UUID.$id.Lv.$str.Exp", 0)
    }
}