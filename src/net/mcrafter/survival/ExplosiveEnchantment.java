package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

class ExplosiveEnchantment extends CustomEnchantment implements Listener
{
    private static Material m_applyTo[] = { Material.BOW };
    private JavaPlugin m_plugin;

    ExplosiveEnchantment(JavaPlugin p_plugin)
    {
        super("Explosive", m_applyTo, "arrow effect");
        max = 3;
        base = 30;
        this.interval = 10;
        m_plugin = p_plugin;
    }

    @Override
    public void applyEffect(LivingEntity p_user, LivingEntity p_target, int p_enchantLevel, EntityDamageByEntityEvent p_event)
    {
        Bukkit.getServer().getPluginManager().registerEvents(this, m_plugin);
        p_target.getWorld().createExplosion(p_target.getLocation(), p_enchantLevel);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event)
    {
        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }

}
