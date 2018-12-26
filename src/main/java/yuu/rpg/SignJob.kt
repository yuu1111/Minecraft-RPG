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
    private var config: FileConfiguration? = null
    private var job: String? = null
    var uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val clickedBlock = e.clickedBlock
        val material = clickedBlock.type
        if (material == Material.SIGN || material == Material.WALL_SIGN) {
            val sign = clickedBlock.state as Sign
            val line1 = sign.getLine(0)
            var p: Player = e.player
            config = uuid.getConfig()
            id = p.uniqueId
            job = config!!.getString("UUID.$id.Job")

            joblvset(p)
            jobchange(line1, p)
        }
    }

    private fun joblvset(p: Player) {
        ymlcheck(job)
        config!!.set("UUID.$id.Lv.$job.Lv", p.level)
        config!!.set("UUID.$id.Lv.$job.Exp", p.exp)
        uuid.saveConfig()
    }

    private fun jobchange(str: String, p: Player) {

        var job2: String = JobUtil.jobjptoen(str)
        if (!job2.isEmpty()) {
            p.exp = config!!.getDouble("UUID.$id.Lv.$job2.Exp").toFloat()
            p.level = config!!.getInt("UUID.$id.Lv.$job2.Lv")

            config!!.set("UUID.$id.Job", job2)
            uuid.saveConfig()
            p.sendMessage(str+"になりました")
        }
    }

    private fun ymlcheck(str: String?) {

        val lv = config!!.getString("UUID.$id.Lv.$str.Lv")
        val exp = config!!.getString("UUID.$id.Lv.$str.Exp")

        if (lv == null)
            config!!.set("UUID.$id.Lv.$str.Lv", 0)

        if (exp == null)
            config!!.set("UUID.$id.Lv.$str.Exp", 0)
    }
}