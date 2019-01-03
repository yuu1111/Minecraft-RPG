package yuu.rpg.actionbar

import net.minecraft.server.v1_12_R1.ChatMessageType
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player

class ActionbarUtil : Actionbar {

    override fun sendActionbar(p: Player, message: String) {

        val icbc = ChatSerializer.a("{\"text\": \"$message\"}")

        val bar = PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO)

        (p as CraftPlayer).handle.playerConnection.sendPacket(bar)
    }
}