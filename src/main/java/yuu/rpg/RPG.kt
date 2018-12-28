package yuu.rpg

import org.bukkit.plugin.java.JavaPlugin
import yuu.rpg.Entity.CustomEntities


class RPG : JavaPlugin() {

    override fun onEnable() {

        CustomEntities.registerEntities()
        saveDefaultConfig()
        CustomConfig(this, "UUID.yml").saveDefaultConfig()

        Join(this)
        SignJob(this)
        DeathRespawn(this)
        JobSkill(this)
        Skill(this)

        getCommand("job").executor = JobCommand(this)
        getCommand("spawn").executor = Spawn(this)
        getCommand("oputil").executor = OpUtil(this)
    }

    override fun onDisable() {
        CustomEntities.unregisterEntities()
    }
}
