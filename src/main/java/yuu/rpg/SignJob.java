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
    UUID id;
    Player p;
    FileConfiguration config;

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
            Sign sign = (Sign) clickedBlock.getState();
            String line1 = sign.getLine(0);
            uuid = new CustomConfig(plugin, "UUID.yml");
            config = uuid.getConfig();
            p = e.getPlayer();
            id = p.getUniqueId();
            JobChange(line1);
        }
    }

    private void JobChange(String str) {

        switch (str) {

            case "戦士":
                config.set("UUID." + id + ".Job", "Warrior");
                p.sendMessage("戦士になりました");
                uuid.saveConfig();
                break;

            case "魔術師":
                config.set("UUID." + id + ".Job", "Mage");
                p.sendMessage("魔術師になりました");
                uuid.saveConfig();
                break;

            case "狩人":
                config.set("UUID." + id + ".Job", "Hunter");
                p.sendMessage("狩人になりました");
                uuid.saveConfig();
                break;

            case "村人":
                config.set("UUID." + id + ".Job", "Villager");
                p.sendMessage("村人になりました");
                uuid.saveConfig();
                break;

            case "放浪者":
                config.set("UUID." + id + ".Job", "Wanderer");
                p.sendMessage("放浪者になりました");
                uuid.saveConfig();
                break;

            case "騎士":
                config.set("UUID." + id + ".Job", "Knight");
                p.sendMessage("騎士になりました");
                uuid.saveConfig();
                break;

            case "クラフター":
                config.set("UUID." + id + ".Job", "Crafter");
                p.sendMessage("クラフターになりました");
                uuid.saveConfig();
                break;

        }
    }
}