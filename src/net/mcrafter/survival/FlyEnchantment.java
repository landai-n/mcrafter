
package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import org.bukkit.Material;

class FlyEnchantment extends CustomEnchantment
{
    private static Material m_applyTo[] = {
            Material.ELYTRA
            };

    FlyEnchantment()
    {
        super("Fly", m_applyTo);

        max = 1;
        base = 30;
        this.interval = 30;
    }

}