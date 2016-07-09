package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

class ZeusEnchantment extends CustomEnchantment
{
    private static Material m_applyTo[] = { Material.BOW };

    ZeusEnchantment()
    {
        super("Zeus", m_applyTo, "arrow effect");
        max = 1;
        base = 30;
        this.interval = 30;
    }

    @Override
    public void applyEffect(LivingEntity p_user, LivingEntity p_target, int p_enchantLevel, EntityDamageByEntityEvent p_event)
    {
        LightningStrike l_strike = p_target.getWorld().strikeLightning(p_target.getLocation());
    }

}
