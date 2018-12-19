package yuu.rpg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class JobCommand implements CommandExecutor {

    private RPG plugin;
    CustomConfig uuid;
    FileConfiguration config;
    Player p;
    UUID id;
    JobCommand(RPG pl) {
        plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        uuid = new CustomConfig(plugin, "UUID.yml");
        config = uuid.getConfig();
        p = (Player) sender;
        id = p.getUniqueId();

        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("change")) {
                job(args[1]);
            }
            if (args[0].equalsIgnoreCase("check")) {
            p.sendMessage(config.getString("UUID."+ id +".Job"));
            }
        }
        return true;
    }

    public void job(String str) {

        UUID id = p.getUniqueId();

        switch (str) {
            case "warrior":
                config.set("UUID." + id + ".Job", "warrior");
                p.sendMessage("ウォーリアになりました");
                uuid.saveConfig();
                break;
            case "knight":
                config.set("UUID." + id + ".Job", "knight");
                p.sendMessage("ナイトになりました");
                uuid.saveConfig();
                break;
            case "witch":
                config.set("UUID." + id + ".Job", "witch");
                uuid.saveConfig();
                p.sendMessage("ウィッチになりました");
                break;
            case "magician":
                config.set("UUID." + id + ".Job", "magician");
                p.sendMessage("マジシャンになりました");
                uuid.saveConfig();
                break;
            default:
                p.sendMessage("存在しない職業です");
                break;
        }
        return;
    }
}

