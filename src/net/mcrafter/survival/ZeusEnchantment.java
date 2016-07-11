package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;
import java.util.Map;

class ZeusEnchantment extends CustomEnchantment implements Listener
{
    private static Material m_applyTo[] = { Material.BOW };
    private Map<ProjectileSource, Integer> m_ammoMap = new HashMap<>();

    ZeusEnchantment(JavaPlugin p_plugin)
    {
        super("Zeus", m_applyTo, "arrow effect");
        max = 1;
        base = 30;
        this.interval = 30;
        Bukkit.getPluginManager().registerEvents(this, p_plugin);
    }

    @EventHandler
    public void OnProjectileLaunch(EntityShootBowEvent p_event)
    {
        ItemStack l_item = p_event.getBow();

        if (l_item.getType() == Material.BOW)
            if (EnchantmentAPI.itemHasEnchantment(l_item, "Ammo") && EnchantmentAPI.itemHasEnchantment(l_item, "Zeus"))
            {
                AmmoEnchantment.Consume(l_item);
                if (m_ammoMap.containsKey(p_event.getEntity()))
                    m_ammoMap.replace(p_event.getEntity(), m_ammoMap.get(p_event.getEntity()) + 1);
                else
                    m_ammoMap.put(p_event.getEntity(), 1);
            }
    }

    @EventHandler
    public void OnProjectileHit(ProjectileHitEvent p_event)
    {
        if (!m_ammoMap.containsKey(p_event.getEntity().getShooter()))
            return;
        if (m_ammoMap.get(p_event.getEntity().getShooter()) == 1)
            m_ammoMap.remove(p_event.getEntity().getShooter());
        else
            m_ammoMap.replace(p_event.getEntity().getShooter(), m_ammoMap.get(p_event.getEntity().getShooter()) - 1);
        p_event.getEntity().getLocation().getWorld().strikeLightning(p_event.getEntity().getLocation());

    }
}
