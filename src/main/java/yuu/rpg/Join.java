package yuu.rpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.UUID;

public class Join implements Listener {

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
        String X = config.getString("UUID." + id + ".Spawn.x");
        if (X == null){

            config.set("UUID." + id + ".Spawn.x", -315);
            config.set("UUID." + id + ".Spawn.y", 67);
            config.set("UUID." + id + ".Spawn.z", -444);
            uuid.saveConfig();
        }
        if (Job == null) {
            config.set("UUID." + id + ".Job", "Crafter");


            uuid.saveConfig();
            World w = p.getLocation().getWorld();
            Location spawnpoint = new Location(w, -315, 67, -444);
            p.teleport(spawnpoint);
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
                            "aaa\n" +
                            "bbb\n" +
                            "ccc"});
            item.setItemMeta(meta);
            p.getInventory().addItem(item);
        }
    }
}
