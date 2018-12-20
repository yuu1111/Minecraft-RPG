package yuu.rpg;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.PublicKey;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new CustomConfig(this, "UUID.yml").saveDefaultConfig();

        new Join(this);
        new SignJob(this);
        new DeathRespawn(this);

        getCommand("job").setExecutor(new JobCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
