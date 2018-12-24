package yuu.rpg;

import org.bukkit.plugin.java.JavaPlugin;


public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new CustomConfig(this, "UUID.yml").saveDefaultConfig();

        new Join(this);
        new SignJob(this);
        new DeathRespawn(this);
        new JobSkill(this);
        new Skill(this);

        getCommand("job").setExecutor(new JobCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
