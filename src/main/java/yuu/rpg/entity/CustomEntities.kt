package yuu.rpg.entity

import net.minecraft.server.v1_12_R1.Entity
import net.minecraft.server.v1_12_R1.EntityTypes
import net.minecraft.server.v1_12_R1.EntityZombie
import net.minecraft.server.v1_12_R1.MinecraftKey
import org.bukkit.entity.EntityType

enum class CustomEntities private constructor(val name1: String, val id: Int, val entityType: EntityType, private val nmsClass: Class<out Entity>, private val customClass: Class<out Entity>) {

    ZOMBIE1("testZombie", 54, EntityType.ZOMBIE, EntityZombie::class.java, Zombie::class.java);

    private val key: MinecraftKey
    private val oldKey: MinecraftKey?

    init {
        this.key = MinecraftKey(name1)
        this.oldKey = EntityTypes.b.b(nmsClass)
    }

    private fun register() {
        EntityTypes.d.add(key)
        EntityTypes.b.a(id, key, customClass)
    }

    private fun unregister() {
        EntityTypes.d.remove(key)
        EntityTypes.b.a(id, oldKey, nmsClass)
    }

    fun getCustomClass(): Class<*> {
        return customClass
    }

    companion object {

        fun registerEntities() {
            for (ce in CustomEntities.values()) ce.register()
        }

        fun unregisterEntities() {
            for (ce in CustomEntities.values()) ce.unregister()
        }
    }
}

