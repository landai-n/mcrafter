
package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class ImmortalEnchantment extends CustomEnchantment
{
    static Material l_applyTo[] = {
            Material.DIAMOND_SWORD,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SPADE,
            Material.DIAMOND_PICKAXE
            };

    public ImmortalEnchantment()
    {
        super("Immortal", l_applyTo);

        max = 5;
        base = 5;
        this.interval = 5;
    }

}