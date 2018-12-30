package yuu.rpg

import org.bukkit.plugin.java.JavaPlugin
import yuu.rpg.command.JobCommand
import yuu.rpg.command.Spawn
import yuu.rpg.config.CustomConfig
import yuu.rpg.entity.CustomEntities
import yuu.rpg.job.LevelUP
import yuu.rpg.job.SignJob
import yuu.rpg.oputil.OPGUIClick
import yuu.rpg.oputil.OPUtil
import yuu.rpg.other.DeathRespawn
import yuu.rpg.other.Join
import yuu.rpg.skill.Skill
import yuu.rpg.spawnblock.SetSpawnBlock

class RPG : JavaPlugin() {

    override fun onEnable() {

        CustomEntities.registerEntities()
        saveDefaultConfig()
        CustomConfig(this, "UUID.yml").saveDefaultConfig()

        Join(this)
        SignJob(this)
        DeathRespawn(this)
        LevelUP(this)
        Skill(this)
        SetSpawnBlock(this)
        OPGUIClick(this)

        getCommand("job").executor = JobCommand(this)
        getCommand("spawn").executor = Spawn(this)
        getCommand("oputil").executor = OPUtil(this)
    }

    override fun onDisable() {
        CustomEntities.unregisterEntities()
    }
}
