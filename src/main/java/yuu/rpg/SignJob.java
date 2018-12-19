package yuu.rpg;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;


public class SignJob implements Listener {

    private RPG plugin;
    CustomConfig uuid;

    SignJob(RPG pl) {
        plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block clickedBlock = e.getClickedBlock();
        Material material = clickedBlock.getType();
        if (material == Material.SIGN || material == Material.WALL_SIGN) {
            uuid = new CustomConfig(plugin, "UUID.yml");
            FileConfiguration config = uuid.getConfig();
            Player p = e.getPlayer();
            UUID id = p.getUniqueId();
            Sign sign = (Sign) clickedBlock.getState();
            p.sendMessage(sign.getLine(0));
        }
    }
}