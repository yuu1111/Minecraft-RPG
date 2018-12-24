package yuu.rpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class DeathRespawn implements Listener {

    private RPG plugin;
    CustomConfig uuid;
    FileConfiguration config;

    DeathRespawn(RPG pl) {
        plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        p.spigot().respawn();
        uuid = new CustomConfig(plugin, "UUID.yml");
        config = uuid.getConfig();
        UUID id = p.getUniqueId();
        int X = config.getInt("UUID." + id + ".Spawn.x");
        int Y = config.getInt("UUID." + id + ".Spawn.y");
        int Z = config.getInt("UUID." + id + ".Spawn.z");

        Location spawnpoint = new Location(p.getLocation().getWorld(), X, Y, Z);
        p.teleport(spawnpoint);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block clickedBlock = e.getClickedBlock();
        Material material = clickedBlock.getType();
        if (material == Material.STAINED_GLASS) {
            uuid = new CustomConfig(plugin, "UUID.yml");
            Player p = e.getPlayer();
            config = uuid.getConfig();
            UUID id = p.getUniqueId();

            config.set("UUID." + id + ".Spawn.x", e.getClickedBlock().getX());
            config.set("UUID." + id + ".Spawn.y", e.getClickedBlock().getY());
            config.set("UUID." + id + ".Spawn.z", e.getClickedBlock().getZ());
            uuid.saveConfig();
            p.sendMessage("スポーン地点を設定しました");
        }
    }
}
