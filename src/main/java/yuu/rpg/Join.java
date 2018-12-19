package yuu.rpg;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Join  implements Listener {

    private RPG plugin;
    CustomConfig uuid;

    Join(RPG pl) {
        plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {

        uuid = new CustomConfig(plugin, "UUID.yml");
        FileConfiguration config = uuid.getConfig();
        Player p = e.getPlayer();
        UUID id = p.getUniqueId();
        String Job = config.getString("UUID." + id + ".Job");
        p.sendMessage(Job);
        if (Job == null) {
            config.set("UUID." + id + ".Job", "none");
            uuid.saveConfig();
        }
    }
}
