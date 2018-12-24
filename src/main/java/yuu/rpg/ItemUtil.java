package yuu.rpg;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {


    public static ItemStack ItemCreate(String name, Material type, int loreline, String lore1, String lore2) {

        List<String> lores = new ArrayList<String>();

        if (loreline == 1) {
            lores.add(lore1);

        } else if (loreline == 2) {
            lores.add(lore1);
            lores.add(lore2);
        }

        ItemStack item = new ItemStack(type);
        ItemMeta im = item.getItemMeta();
        im.setLore(lores);
        im.setDisplayName(ChatColor.RESET + name);
        item.setItemMeta(im);

        return item;
    }
    public static ItemStack addenc(ItemStack item,Enchantment enc,int enclv){

        item.addUnsafeEnchantment(enc, enclv);

        return item;
    }
}

