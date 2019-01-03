package yuu.rpg.entity

import net.minecraft.server.v1_12_R1.EntityZombie
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld

public open class Zombie : EntityZombie {

    constructor(loc: Location) : super((loc.getWorld() as CraftWorld).handle) {

        setPosition(loc.x, loc.y, loc.z);
    }
}
