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
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;


public class SignJob implements Listener {

    private RPG plugin;
    CustomConfig uuid;
    UUID id;
    Player p;
    FileConfiguration config;
    String Job;

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
            Job = config.getString("UUID." + id + ".Job");

            JobLvSet();
            JobChange(line1);
        }
    }

    private void JobLvSet(){
        ymlcheck(Job);
        config.set("UUID." + id + ".Lv." + Job + "Lv", p.getLevel());
        config.set("UUID." + id + ".Lv."+ Job + "Exp", p.getExp());
        uuid.saveConfig();


    }

    private void JobChange(String str) {



        switch (str) {

            case "戦士":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.WarriorExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.WarriorLv"));

                config.set("UUID." + id + ".Job", "Warrior");
                p.sendMessage("戦士になりました");
                break;

            case "魔術師":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.MageExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.MageLv"));

                config.set("UUID." + id + ".Job", "Mage");
                p.sendMessage("魔術師になりました");
                break;

            case "狩人":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.HunterExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.HunterLv"));

                config.set("UUID." + id + ".Job", "Hunter");
                p.sendMessage("狩人になりました");
                break;

            case "村人":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.VillagerExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.VillagerLv"));

                config.set("UUID." + id + ".Job", "Villager");
                p.sendMessage("村人になりました");
                break;

            case "放浪者":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.WandererExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.WandererLv"));

                config.set("UUID." + id + ".Job", "Wanderer");
                p.sendMessage("放浪者になりました");
                break;

            case "騎士":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.KnightExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.KnightLv"));

                config.set("UUID." + id + ".Job", "Knight");
                p.sendMessage("騎士になりました");
                break;

            case "クラフター":

                p.setExp((float) config.getDouble("UUID." + id + ".Lv.CrafterExp"));
                p.setLevel(config.getInt("UUID." + id + ".Lv.CrafterLv"));

                config.set("UUID." + id + ".Job", "Crafter");
                p.sendMessage("クラフターになりました");
                break;

        }
        uuid.saveConfig();
    }

    private void ymlcheck(String str) {

        String Lv = config.getString("UUID." + id + ".Lv." + str + "Lv");
        String Exp = config.getString("UUID." + id + ".Lv." + str + "Exp");

        if (Lv == null)
            config.set("UUID." + id + ".Lv." + str + "Lv", 0);

        if (Exp == null)
            config.set("UUID." + id + ".Lv." + str + "Exp", 0);
    }
}