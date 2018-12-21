package yuu.rpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

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
        Location spawnpoint = new Location(p.getLocation().getWorld(), -315, 67, -444);
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
