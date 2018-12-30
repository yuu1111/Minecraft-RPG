package yuu.rpg.config

import org.bukkit.configuration.file.FileConfiguration
import java.util.*

object ymlcheck {

     fun check(config: FileConfiguration?,str: String?,id:UUID?) {

        if (config!!.getString("UUID.$id.Lv.$str.Lv") == null)
            config.set("UUID.$id.Lv.$str.Lv", 0)

        if (config.getString("UUID.$id.Lv.$str.Exp") == null)
            config.set("UUID.$id.Lv.$str.Exp", 0)

         if ( config.getString("UUID.$id.Lv.$str.SkillP") == null)
             config.set("UUID.$id.Lv.$str.SkillP", 0)
    }
}