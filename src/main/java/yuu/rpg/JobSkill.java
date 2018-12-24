package yuu.rpg;

import jdk.nashorn.internal.scripts.JO;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.UUID;

public class JobSkill  implements Listener {

    private RPG plugin;
    CustomConfig uuid;
    UUID id;
    FileConfiguration config;

    JobSkill(RPG pl) {
        plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent e) {


        int nl = e.getNewLevel();
        int ol = e.getOldLevel();


        uuid = new CustomConfig(plugin, "UUID.yml");
        config = uuid.getConfig();
        Player p = e.getPlayer();
        id = p.getUniqueId();
        String Job = config.getString("UUID." + id + ".Job");
        int Lv = config.getInt("UUID." + id + ".Lv." + Job + ".Lv");
        p.sendMessage(Job + "のレベルが" + p.getLevel() + "になりました");

        String SkillP = config.getString("UUID." + id + ".Lv." + Job + ".SkillP");
        if (SkillP == null) {
            config.set("UUID." + id + ".Lv." + Job + ".SkillP", 0);
            SkillP = config.getString("UUID." + id + ".Lv." + Job + ".SkillP");
        }

        config.set("UUID." + id + ".Lv." + Job + ".Lv", p.getLevel());
        config.set("UUID." + id + ".Lv." + Job + ".Exp", p.getExp());
        config.set("UUID." + id + ".Lv." + Job + ".SkillP", Integer.parseInt(SkillP) + (nl - ol * 3));
        uuid.saveConfig();

    }
}