package yuu.rpg.job

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLevelChangeEvent
import yuu.rpg.config.CustomConfig
import yuu.rpg.config.ymlcheck
import yuu.rpg.RPG

import java.util.UUID

class LevelUP internal constructor(private val plugin: RPG) : Listener {


    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    var uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private var id: UUID? = null
    internal var config: FileConfiguration? = uuid.getConfig()

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLevelChangeEvent(e: PlayerLevelChangeEvent) {

        val p = e.player
        id = p.uniqueId
        val job = config!!.getString("UUID.$id.Job")
        p.sendMessage(job + "のレベルが" + p.level + "になりました")

        ymlcheck.check(config,job,id)

       // val skill: String? = config!!.getString("UUID.$id.Lv.$job.SkillP")

        config!!.set("UUID.$id.Lv.$job.Lv", p.level)
        config!!.set("UUID.$id.Lv.$job.Exp", p.exp)
        uuid.saveConfig()

    }
}