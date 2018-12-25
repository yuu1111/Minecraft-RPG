package yuu.rpg

import org.bukkit.Bukkit
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
    private var id: UUID? = null
    private var config: FileConfiguration? = null
    private var job: String? = null
    var uuid: CustomConfig = CustomConfig(plugin, "UUID.yml")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {


        uuid = CustomConfig(plugin, "UUID.yml")
        config = uuid.getConfig()
       var p = sender as Player
        id = p.uniqueId

        if (args.size != 0) {
            if (args[0].equals("check", ignoreCase = true)) {
                p.sendMessage(config!!.getString("UUID.$id.Job"))
            } else if (args[0].equals("book", ignoreCase = true)) {
                // プレイヤーに本を作成して渡す
                val item = ItemStack(Material.WRITTEN_BOOK)
                val meta = item.itemMeta as BookMeta
                meta.author = "yuu_111"
                meta.displayName = "チュートリアル"
                meta.addPage(*arrayOf(
                        // 1ページ目
                        "チュートリアル\n" +
                                "まず最初に奥の方の看板で職業を選びましょう\n" +
                                "各職業についての説明は次のページ以降にあります",
                        // 2ページ目
                        "戦士\n" +
                                "\n" +
                                "基本的な剣などの武器を扱える職業です\n" +
                                "高い筋力を生かして弓を高い威力で使えます\n" +
                                "ccc"))
                item.itemMeta = meta
                p.inventory.addItem(item)
            } else if (args[0].equals("spawn", ignoreCase = true)) {
                config!!.set("UUID.$id.Spawn.x", -315)
                config!!.set("UUID.$id.Spawn.y", 67)
                config!!.set("UUID.$id.Spawn.z", -444)
            } else if (args[0].equals("exp", ignoreCase = true)) {
                val exp = p.exp
                val exp2 = exp.toString()
                val plv = p.level
                val plv2 = plv.toString()
                p.sendMessage("経験値:$exp2")
                p.sendMessage("レベル:$plv2")

            } else if (args[0].equals("gui", ignoreCase = true)) {
                val inv: Inventory
                inv = Bukkit.createInventory(null, 54, "Item")
                inv.setItem(0, ItemUtil.ItemCreate("test", Material.STONE, 2, "aaa", "bbb"))
                p.openInventory(inv)
            }
        }
        return true
    }
}


