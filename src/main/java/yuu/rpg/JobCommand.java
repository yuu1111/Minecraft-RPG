package yuu.rpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
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
            if (args[0].equalsIgnoreCase("check")) {
                p.sendMessage(config.getString("UUID." + id + ".Job"));
            } else if (args[0].equalsIgnoreCase("book")) {
                // プレイヤーに本を作成して渡す
                ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) item.getItemMeta();
                meta.setAuthor("yuu_111");
                meta.setDisplayName("チュートリアル");
                meta.addPage(new String[]{
                        // 1ページ目
                        "チュートリアル\n" +
                                "まず最初に奥の方の看板で職業を選びましょう\n" +
                                "各職業についての説明は次のページ以降にあります",
                        // 2ページ目
                        "戦士\n" +
                                "\n" +
                                "基本的な剣などの武器を扱える職業です\n" +
                                "高い筋力を生かして弓を高い威力で使えます\n" +
                                "ccc"});
                item.setItemMeta(meta);
                p.getInventory().addItem(item);
            } else if (args[0].equalsIgnoreCase("spawn")) {
                config.set("UUID." + id + ".Spawn.x", -315);
                config.set("UUID." + id + ".Spawn.y", 67);
                config.set("UUID." + id + ".Spawn.z", -444);
            } else if (args[0].equalsIgnoreCase("exp")) {
                float exp = p.getExp();
                String exp2 = String.valueOf(exp);
                int plv = p.getLevel();
                String plv2 = String.valueOf(plv);
                p.sendMessage("経験値:" + exp2);
                p.sendMessage("レベル:" + plv2);

            } else if (args[0].equalsIgnoreCase("gui")) {
                Inventory inv;
                inv = Bukkit.createInventory(null, 54, "Item");
                inv.setItem(0,ItemUtil.ItemCreate("test",Material.STONE,2,"aaa","bbb"));
                p.openInventory(inv);
            }
        }
        return true;
    }
}


