package yuu.rpg.Skill

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import yuu.rpg.RPG

class Skill internal constructor(private val plugin: RPG) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {

        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val p = e.player
        if (p.inventory.itemInMainHand.type == Material.WOOD_SWORD) {
            val loc = e.clickedBlock.location

            loc.world.spawnParticle(
                    Particle.VILLAGER_HAPPY,
                    loc,
                    500,
                    5.0, // 散開させるXの範囲
                    1.0, // 散開させるYの範囲
                    5.0, // 散開させるZの範囲
                    5.0 // 速度?
            );

            val list = p.getNearbyEntities(5.0, 1.0, 5.0)
            for (i in list.indices) {
                val en = list[i] as Entity
                if (en.type.isAlive) {
                    (en as Damageable).damage(10.0)
                }
            }
        }
    }
} 
