package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AmmoEnchantment extends CustomEnchantment
{
    private static Material m_applyTo[] = { Material.BOW };

    AmmoEnchantment()
    {
        super("Ammo", m_applyTo);
        max = 16;
        base = 50;
        RomanNumber = false;
        this.interval = 1;
    }

    static void Consume(ItemStack p_item)
    {
        CustomEnchantment l_ammo = EnchantmentAPI.getEnchantment("Ammo");
        int l_level = EnchantmentAPI.getItemEnchantmentLevel(p_item, "Ammo");

        System.out.println("Consume from " + l_level);
        if (l_level-- == 0)
            return;
        l_ammo.removeFromItem(p_item);
        if (l_level > 0)
            l_ammo.addToItem(p_item, l_level);
        System.out.println("Ammo: " + l_level);
    }
}
