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

            JobLvSet(p)
            JobChange(line1, p)
        }
    }

    private fun JobLvSet(p: Player) {
        ymlcheck(job)
        config!!.set("UUID.$id.Lv.$job.Lv", p.level)
        config!!.set("UUID.$id.Lv.$job.Exp", p.exp)
        uuid.saveConfig()
    }

    private fun JobChange(str: String, p: Player) {


        when (str) {

            "戦士" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Warrior.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Warrior.Lv")

                config!!.set("UUID.$id.Job", "Warrior")
                p.sendMessage("戦士になりました")
            }

            "魔術師" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Mage.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Mage.Lv")

                config!!.set("UUID.$id.Job", "Mage")
                p.sendMessage("魔術師になりました")
            }

            "狩人" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Hunter.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Hunter.Lv")

                config!!.set("UUID.$id.Job", "Hunter")
                p.sendMessage("狩人になりました")
            }

            "村人" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Villager.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Villager.Lv")

                config!!.set("UUID.$id.Job", "Villager")
                p.sendMessage("村人になりました")
            }

            "放浪者" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Wanderer.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Wanderer.Lv")

                config!!.set("UUID.$id.Job", "Wanderer")
                p.sendMessage("放浪者になりました")
            }

            "騎士" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Knight.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Knight.Lv")

                config!!.set("UUID.$id.Job", "Knight")
                p.sendMessage("騎士になりました")
            }

            "クラフター" -> {

                p.exp = config!!.getDouble("UUID.$id.Lv.Crafter.Exp").toFloat()
                p.level = config!!.getInt("UUID.$id.Lv.Crafter.Lv")

                config!!.set("UUID.$id.Job", "Crafter")
                p.sendMessage("クラフターになりました")
            }
        }
        uuid.saveConfig()
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