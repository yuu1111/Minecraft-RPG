package yuu.rpg

import jdk.nashorn.internal.scripts.JO
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerLevelChangeEvent

import java.util.UUID

class JobSkill internal constructor(private val plugin: RPG) : Listener {


    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    var uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private var id: UUID? = null
    internal var config: FileConfiguration? = null

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLevelChangeEvent(e: PlayerLevelChangeEvent) {


        val nl = e.newLevel
        val ol = e.oldLevel


        uuid = CustomConfig(plugin, "UUID.yml")
        config = uuid.getConfig()
        val p = e.player
        id = p.uniqueId
        val Job = config!!.getString("UUID.$id.Job")
        val Lv = config!!.getInt("UUID.$id.Lv.$Job.Lv")
        p.sendMessage(Job + "のレベルが" + p.level + "になりました")

        var SkillP: String? = config!!.getString("UUID.$id.Lv.$Job.SkillP")
        if (SkillP == null) {
            config!!.set("UUID.$id.Lv.$Job.SkillP", 0)
            SkillP = config!!.getString("UUID.$id.Lv.$Job.SkillP")
        }

        config!!.set("UUID.$id.Lv.$Job.Lv", p.level)
        config!!.set("UUID.$id.Lv.$Job.Exp", p.exp)
        config!!.set("UUID.$id.Lv.$Job.SkillP", Integer.parseInt(SkillP!!) + (nl - ol * 3))
        uuid.saveConfig()

    }
}