package yuu.rpg;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathRespawn implements Listener {
        @EventHandler
        public void onPlayerDeathEvent(PlayerDeathEvent e) {
            Player p = e.getEntity().getPlayer();
            p.spigot().respawn();
        }
}
