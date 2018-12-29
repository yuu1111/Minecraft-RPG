package yuu.rpg.Entity

import com.sun.awt.SecurityWarning.setPosition
import net.minecraft.server.v1_12_R1.EntityZombie
import net.minecraft.server.v1_12_R1.World
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import yuu.rpg.RPG


public open class Zombie : EntityZombie {

    constructor(loc: Location) : super((loc.getWorld() as CraftWorld).handle) {

        setPosition(loc.getX(), loc.getY(), loc.getZ());
    }
}
