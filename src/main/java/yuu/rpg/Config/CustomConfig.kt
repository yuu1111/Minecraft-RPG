package yuu.rpg.Config

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.logging.Level

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin

class CustomConfig
/**
 * 指定したファイル名で設定を読み書きするカスタムコンフィグクラスをインスタンス化します。
 *
 * @param plugin
 * ロード対象のプラグイン
 * @param fileName
 * 読み込みファイル名
 */
@JvmOverloads constructor(private val plugin: Plugin, private val file: String = "config.yml") {

    private var config: FileConfiguration? = null
    private val configFile: File

    init {
        configFile = File(plugin.dataFolder, file)
    }

    /**
     * デフォルト設定を保存します。
     */
    fun saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(file, false)
        }
    }

    /**
     * 読み込んだFileConfiguretionを提供します。
     *
     * @return 読み込んだ設定
     */
    fun getConfig(): FileConfiguration? {
        if (config == null) {
            reloadConfig()
        }
        return config
    }

    /**
     * 設定を保存します。
     */
    fun saveConfig() {
        if (config == null) {
            return
        }
        try {
            getConfig()!!.save(configFile)
        } catch (ex: IOException) {
            plugin.logger.log(Level.SEVERE, "Could not save config to $configFile", ex)
        }

    }

    /**
     * 設定をリロードします。
     */
    fun reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile)

        val defConfigStream = plugin.getResource(file) ?: return

        config!!.defaults = YamlConfiguration.loadConfiguration(InputStreamReader(defConfigStream, StandardCharsets.UTF_8))
    }
}
/**
 * config.ymlを設定として読み書きするカスタムコンフィグクラスをインスタンス化します。
 *
 * @param plugin
 * ロード対象のプラグイン
 */
