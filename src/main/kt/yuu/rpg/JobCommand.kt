package yuu.rpg

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

import java.util.UUID

class JobCommand internal constructor(private val plugin: RPG) : CommandExecutor {
    private val uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")
    private val config: FileConfiguration? = uuid.getConfig()
    private var id: UUID? = null
    private var job: String? = null

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val p = sender as Player
        id = p.uniqueId
        val inv: Inventory
        if (args.isNotEmpty()) {
            when {
                args[0].equals("check", ignoreCase = true) -> {
                    p.sendMessage("経験値:" + p.exp.toString())
                    p.sendMessage("レベル:" + p.level.toString())
                    p.sendMessage(config!!.getString("UUID.$id.Job"))
                }
                args[0].equals("book", ignoreCase = true) -> {
                    // プレイヤーに本を作成して渡す
                    val item = ItemStack(Material.WRITTEN_BOOK)
                    val meta = item.itemMeta as BookMeta
                    meta.author = "yuu_111"
                    meta.displayName = "チュートリアル"
                    meta.addPage(*arrayOf(
                            "チュートリアル\nまず最初に奥の方の看板で職業を選びましょう\n各職業についての説明は次のページ以降にあります",
                            "戦士\n\n基本的な剣などの武器を扱える職業です\n高い筋力を生かして弓を高い威力で使えます\nccc"))
                    item.itemMeta = meta
                    p.inventory.addItem(item)
                }
            }
        }
        return true
    }
}


