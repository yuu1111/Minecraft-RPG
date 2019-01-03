package yuu.rpg.other

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import yuu.rpg.config.CustomConfig
import yuu.rpg.RPG

class Join internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(e: PlayerJoinEvent) {

        val uuid = CustomConfig(plugin, "UUID.yml")
        val config = uuid.getConfig()
        val p = e.player
        val id = p.uniqueId
        val job = config!!.getString("UUID.$id.Job")

        if (config.getString("UUID.$id.Spawn.x") == null || config.getString("UUID.$id.Spawn.y") == null || config.getString("UUID.$id.Spawn.z") == null) {
            config.set("UUID.$id.Spawn.x", -315)
            config.set("UUID.$id.Spawn.y", 67)
            config.set("UUID.$id.Spawn.z", -444)
            uuid.saveConfig()
        }

        if (job == null) {
            config.set("UUID.$id.Job", "Wanderer")
            uuid.saveConfig()
            p.teleport(Location(p.location.world, config.getDouble("UUID.$id.Spawn.x"), config.getDouble("UUID.$id.Spawn.y"), config.getDouble("UUID.$id.Spawn.z")))

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
    }
}
