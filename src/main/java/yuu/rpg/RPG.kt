package yuu.rpg

import org.bukkit.plugin.java.JavaPlugin
import yuu.rpg.Command.JobCommand
import yuu.rpg.Command.Spawn
import yuu.rpg.Config.CustomConfig
import yuu.rpg.Entity.CustomEntities
import yuu.rpg.Job.JobSkill
import yuu.rpg.Job.SignJob
import yuu.rpg.OPUtil.OPGUIClick
import yuu.rpg.OPUtil.OpUtil
import yuu.rpg.Other.DeathRespawn
import yuu.rpg.Other.Join
import yuu.rpg.Skill.Skill
import yuu.rpg.SpawnBlock.SetSpawnBlock


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
        SetSpawnBlock(this)
        OPGUIClick(this)

        getCommand("job").executor = JobCommand(this)
        getCommand("spawn").executor = Spawn(this)
        getCommand("oputil").executor = OpUtil(this)
    }

    override fun onDisable() {
        CustomEntities.unregisterEntities()
    }
}
