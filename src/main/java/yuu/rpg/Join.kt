package yuu.rpg

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

import java.util.UUID

class Join internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(e: PlayerJoinEvent) {

        val config = uuid.getConfig()
        val p = e.player
        val id = p.uniqueId
        val job = config!!.getString("UUID.$id.Job")
        val x = config.getString("UUID.$id.Spawn.x")
        p.sendMessage(x)
        if (x == null) {
            config.set("UUID.$id.Spawn.x", -315)
            config.set("UUID.$id.Spawn.y", 67)
            config.set("UUID.$id.Spawn.z", -444)
            uuid.saveConfig()
        }
        if (job == null) {
            config.set("UUID.$id.Job", "Wanderer")
            uuid.saveConfig()
            val spawnpoint = Location(p.location.world, -315.0, 67.0, -444.0)
            p.teleport(spawnpoint)

            val item = ItemStack(Material.WRITTEN_BOOK)
            val meta = item.itemMeta as BookMeta
            meta.author = "yuu_111"
            meta.displayName = "チュートリアル"
            meta.addPage(
                    "チュートリアル\nまず最初に奥の方の看板で職業を選びましょう\n各職業についての説明は次のページ以降にあります",
                    "戦士\n\n基本的な剣などの武器を扱える職業です\n高い筋力を生かして弓を高い威力で使えます\nccc")
            item.itemMeta = meta
            p.inventory.addItem(item)
        }
        var hp = config.getString("UUID.$id.Lv.$job.HP")
        if (hp == null) {
            config.set("UUID.$id.Lv.$job.HP", 20)
            config.set("UUID.$id.Lv.$job.MP", 20)

        }
    }
}
