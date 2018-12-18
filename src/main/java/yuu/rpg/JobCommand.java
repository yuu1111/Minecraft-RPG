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

    public JobCommand(RPG pl) {
        plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        uuid = new CustomConfig(plugin, "UUID.yml");
        FileConfiguration config = uuid.getConfig();
        Player p = (Player) sender;
        UUID id = p.getUniqueId();

        plugin.saveConfig();
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("change")) {
                    switch (args[1]) {

                        case "warrior":
                            config.set("UUID." + id + ".Job", "warrior");
                            break;
                        case "knight":
                            config.set("UUID." + id + ".Job", "knight");
                            break;
                        case "witch":
                            config.set("UUID." + id + ".Job", "witch");
                            break;
                        case "magician":
                            config.set("UUID." + id + ".Job", "magician");
                            break;
                            default:

                                break;
                    }
                }
            }

            return true;
        }
    }

