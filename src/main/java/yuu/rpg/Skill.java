package yuu.rpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class Skill implements Listener {

    private RPG plugin;

    Skill(RPG pl) {
        plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.WOOD_SWORD) {
            Location loc = e.getClickedBlock().getLocation();

            loc.getWorld().spawnParticle(
                    Particle.VILLAGER_HAPPY,
                    loc,
                    500,
                    5, // 散開させるXの範囲
                    1, // 散開させるYの範囲
                    5 // 散開させるZの範囲
            );

            List list = p.getNearbyEntities(5, 1, 5);
            for (int i = 0; i < list.size(); ++i) {
                Entity en = (Entity) list.get(i);
                if (en.getType().isAlive()) {
                    ((Damageable) en).damage(10);
                }
            }
        }
    }
}
