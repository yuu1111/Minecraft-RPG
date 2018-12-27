package yuu.rpg

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

object ActionBar {

    fun sendActionBar(p: Player, message: String) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message))
    }

    fun statusActionBar(p: Player, HP: String, MP: String) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("HP:" + HP + "MP:" + MP))
    }
}
