package yuu.rpg.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.material.MaterialData

import java.util.*

/**
 * NOTICE: This utility was developer as part of AeolusLib. While you can use it for your own projects, You are NOT allowed to delete or move this header comment.
 *
 * Utility:
 * Chainable [ItemStack]s
 *
 * Example Usage(s):
 * `ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM).amount(1).durability(3).skullOwner("MCAeolus").name(ChatColor.RED+"MCAeolus's Skull").make())`
 * `ItemStack itemStack = new ItemBuilder().type(Material.BEDROCK).lores(new String[]{"Lore1",ChatColor.RED+"Lore2"}).enchantment(Enchantment.DAMAGE_ALL, 99).make()`
 *
 * @author MCAeolus
 * @version 1.0
 */

class ItemBuilder {

    private val item: ItemStack
    private val itemM: ItemMeta

    /**
     * Init item chainable via given Material parameter.
     *
     * @param itemType the [Material] to initiate the instance with.
     * @since 1.0
     */
    constructor(itemType: Material) {
        item = ItemStack(itemType)
        itemM = item.itemMeta
    }

    /**
     * Init item chainable via given ItemStack parameter.
     *
     * @param itemStack the [ItemStack] to initialize the instance with.
     * @since 1.0
     */
    constructor(itemStack: ItemStack) {
        item = itemStack
        itemM = item.itemMeta
    }

    /**
     * Init the item chainable with no defined Material/ItemStack
     *
     * @since 1.0
     */
    constructor() {
        item = ItemStack(Material.AIR)
        itemM = item.itemMeta
    }

    /**
     * Changes the Material type of the [ItemStack]
     *
     * @param material the new [Material] to set for the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun type(material: Material): ItemBuilder {
        make().type = material
        return this
    }

    /**
     * Changes the [ItemStack]s size.
     *
     * @param itemAmt the new Integer count of the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun amount(itemAmt: Int?): ItemBuilder {
        make().amount = itemAmt!!
        return this
    }

    /**
     * Changes the [ItemStack]s display name.
     *
     * @param name the new String for the ItemStack's display name to be set to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun name(name: String): ItemBuilder {
        meta().displayName = name
        make().itemMeta = meta()
        return this
    }

    /**
     * Adds a line of lore to the [ItemStack]
     *
     * @param lore String you want to add to the ItemStack's lore.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun lore(lore: String): ItemBuilder {
        var lores: MutableList<String>? = meta().lore
        if (lores == null) {
            lores = ArrayList()
        }
        lores.add(lore)
        meta().lore = lores
        make().itemMeta = meta()
        return this
    }

    /**
     * Clears the [ItemStack]s lore and replaces it with the defined String array.
     *
     * @param lores String array you want to set the ItemStack's lore to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun lores(lores: Array<String>): ItemBuilder {
        var loresList: MutableList<String>? = meta().lore
        if (loresList == null) {
            loresList = ArrayList()
        } else {
            loresList.clear()
        }
        Collections.addAll(loresList, *lores)
        meta().lore = loresList
        return this
    }

    /**
     * Changes the durability of the current [ItemStack]
     *
     * @param durability the new int amount to set the ItemStack's durability to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun durability(durability: Int): ItemBuilder {
        make().durability = durability.toShort()
        return this
    }

    /**
     * Changes the data value of the [ItemStack]
     *
     * @param data the new int data value (parsed as byte) to set the ItemStack's durability to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun data(data: Int): ItemBuilder {
        make().data = MaterialData(make().type, data.toByte())
        return this
    }

    /**
     * Adds and UnsafeEnchantment to the [ItemStack] with a defined level int value.
     *
     * @param enchantment the [Enchantment] to add to the ItemStack.
     * @param level       the int amount that the Enchantment's level will be set to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun enchantment(enchantment: Enchantment, level: Int): ItemBuilder {
        make().addUnsafeEnchantment(enchantment, level)
        return this
    }

    /**
     * Adds and UnsafeEnchantment to the {@Link} with a level int value of 1.
     *
     * @param enchantment the [Enchantment] to add to the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun enchantment(enchantment: Enchantment): ItemBuilder {
        make().addUnsafeEnchantment(enchantment, 1)
        return this
    }

    /**
     * Clears all [Enchantment]s from the current [ItemStack] then adds the defined array of Enchantments to the ItemStack.
     *
     * @param enchantments the Enchantment array to replace any current enchantments applied on the ItemStack.
     * @param level        the int level value for all Enchantments to be set to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun enchantments(enchantments: Array<Enchantment>, level: Int): ItemBuilder {
        make().enchantments.clear()
        for (enchantment in enchantments) {
            make().addUnsafeEnchantment(enchantment, level)
        }
        return this
    }

    /**
     * Clears all [Enchantment]s from the current [ItemStack] then adds the defined array of Enchantments to the ItemStack with a level int value of 1.
     *
     * @param enchantments the Enchantment array to replace any current enchantments applied on the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun enchantments(enchantments: Array<Enchantment>): ItemBuilder {
        make().enchantments.clear()
        for (enchantment in enchantments) {
            make().addUnsafeEnchantment(enchantment, 1)
        }
        return this
    }

    /**
     * Clears the defined [Enchantment] from the [ItemStack]
     *
     * @param enchantment the Enchantment to remove from the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun clearEnchantment(enchantment: Enchantment): ItemBuilder {
        val itemEnchantments = make().enchantments
        for (enchantmentC in itemEnchantments.keys) {
            if (enchantment === enchantmentC) {
                itemEnchantments.remove(enchantmentC)
            }
        }
        return this
    }

    /**
     * Clears all [Enchantment]s from the [ItemStack]
     *
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun clearEnchantments(): ItemBuilder {
        make().enchantments.clear()
        return this
    }

    /**
     * Clears the defined [String] of lore from the [ItemStack]
     *
     * @param lore the String to be removed from the ItemStack.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun clearLore(lore: String): ItemBuilder {
        if (meta().lore.contains(lore)) {
            meta().lore.remove(lore)
        }
        make().itemMeta = meta()
        return this
    }

    /**
     * Clears all lore [String]s from the [ItemStack]
     *
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun clearLores(): ItemBuilder {
        meta().lore.clear()
        make().itemMeta = meta()
        return this
    }

    /**
     * Sets the [Color] of any LEATHER_ARMOR [Material] types of the [ItemStack]
     *
     * @param color the Color to set the LEATHER_ARMOR ItemStack to.
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun color(color: Color): ItemBuilder {
        if (make().type == Material.LEATHER_HELMET
                || make().type == Material.LEATHER_CHESTPLATE
                || make().type == Material.LEATHER_LEGGINGS
                || make().type == Material.LEATHER_BOOTS) {
            val meta = meta() as LeatherArmorMeta
            meta.color = color
            make().itemMeta = meta
        }
        return this
    }

    /**
     * Clears the [Color] of any LEATHER_ARMOR [Material] types of the [ItemStack]
     *
     * @return the current instance for chainable application.
     * @since 1.0
     */
    fun clearColor(): ItemBuilder {
        if (make().type == Material.LEATHER_HELMET
                || make().type == Material.LEATHER_CHESTPLATE
                || make().type == Material.LEATHER_LEGGINGS
                || make().type == Material.LEATHER_BOOTS) {
            val meta = meta() as LeatherArmorMeta
            meta.color = null
            make().itemMeta = meta
        }
        return this
    }

    /**
     * Sets the skullOwner [SkullMeta] of the current SKULL_ITEM [Material] type [ItemStack]
     *
     * @param name the [String] value to set the SkullOwner meta to for the SKULL_ITEM Material type ItemStack.
     * @return the current instance for chainable application
     * @since 1.0
     */
    fun skullOwner(name: String): ItemBuilder {
        if (make().type == Material.SKULL_ITEM && make().durability == 3.toByte().toShort()) {
            val skullMeta = meta() as SkullMeta
            skullMeta.owner = name
            make().itemMeta = meta()
        }
        return this
    }

    /**
     * Returns the [ItemMeta] of the [ItemStack]
     *
     * @return the ItemMeta of the ItemStack.
     */
    fun meta(): ItemMeta {
        return itemM
    }

    /**
     * Returns the [ItemStack] of the [ItemBuilder] instance.
     *
     * @return the ItemStack of the ItemBuilder instance.
     */
    fun make(): ItemStack {
        return item
    }

}
