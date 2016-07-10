
package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

class ImmortalEnchantment extends CustomEnchantment
{
    private static Material m_applyTo[] = {
            Material.DIAMOND_SWORD,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SPADE,
            Material.DIAMOND_PICKAXE,
            Material.FISHING_ROD,
            Material.IRON_SWORD,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_PICKAXE,
            Material.IRON_SPADE,
            Material.BOW,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_BOOTS,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_HELMET,
            Material.IRON_BOOTS,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_BOOTS,
            Material.ELYTRA,
            Material.SHIELD
            };

    ImmortalEnchantment()
    {
        super("Immortal", m_applyTo);

        max = 5;
        base = 10;
        this.interval = 8;
    }

}