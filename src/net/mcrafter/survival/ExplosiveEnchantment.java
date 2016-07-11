package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExplosiveEnchantment extends CustomEnchantment implements Listener
{
    private static Material m_applyTo[] = { Material.BOW };
    private JavaPlugin m_plugin;
    private Map<ProjectileSource, Integer> m_ammoMap = new HashMap<>();

    ExplosiveEnchantment(JavaPlugin p_plugin)
    {
        super("Explosive", m_applyTo, "arrow effect");
        max = 1;
        base = 30;
        this.interval = 10;
        m_plugin = p_plugin;
    }

    @EventHandler
    public void OnProjectileLaunch(EntityShootBowEvent p_event)
    {
        ItemStack l_item = p_event.getBow();

        if (l_item.getType() == Material.BOW)
            if (EnchantmentAPI.itemHasEnchantment(l_item, "Ammo") && EnchantmentAPI.itemHasEnchantment(l_item, "Explosive"))
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
        Bukkit.getServer().getPluginManager().registerEvents(this, m_plugin);
        p_event.getEntity().getWorld().createExplosion(p_event.getEntity().getLocation(), 2);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event)
    {
        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }

}
