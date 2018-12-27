package yuu.rpg

import org.bukkit.plugin.java.JavaPlugin


class RPG : JavaPlugin() {

    override fun onEnable() {

        saveDefaultConfig()
        CustomConfig(this, "UUID.yml").saveDefaultConfig()

        Join(this)
        SignJob(this)
        DeathRespawn(this)
        JobSkill(this)
        Skill(this)

        getCommand("job").executor = JobCommand(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
