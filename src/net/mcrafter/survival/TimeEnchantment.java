package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import net.minecraft.server.v1_10_R1.Vector3f;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

class TimeEnchantment extends CustomEnchantment
{
    private static Material m_applyTo[] = {Material.WATCH};
    private static Hashtable<String, Long> m_timers = new Hashtable<>();
    private JavaPlugin m_plugin;

    TimeEnchantment(JavaPlugin p_plugin) {
        super("Time", m_applyTo);
        max = 5;
        base = 10;
        this.interval = 8;
        m_plugin = p_plugin;
    }

    @Override
    public void applyMiscEffect(Player p_user, int p_enchantLevel, PlayerInteractEvent p_event)
    {
        if (p_event.getAction() == Action.RIGHT_CLICK_BLOCK || p_event.getAction() == Action.RIGHT_CLICK_AIR)
        {
            if (isOnCooldown(p_user.getName(), 5000))   // 1000 pour une seconde
                return ;
            setOnCooldown(p_user.getName());
            p_user.playSound(p_user.getLocation(), Sound.AMBIENT_CAVE, 1, 1);

            LivingEntity bob;
            List<Entity> l_entities = new ArrayList<>();
            List<Vector> l_velocities = new ArrayList<>();
            int l_range = p_enchantLevel * 1;
            int l_duration = p_enchantLevel * 16;   // 20 ticks pour une seconde

            for (Entity entity : p_user.getNearbyEntities(l_range, l_range, l_range))
            {
                l_entities.add(entity);
                l_velocities.add(entity.getVelocity());
                if (entity instanceof Player)
                {
                    bob = (LivingEntity) entity;
                    bob.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, l_duration, 5));
                    bob.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, l_duration, -1));    // curable au lait ?
                }
                else
                {
                    entity.setGravity(false);
                    entity.setVelocity(getReducedVector(entity.getVelocity()));
                    if (entity instanceof LivingEntity)
                    {
                        bob = (LivingEntity) entity;
                        bob.setAI(false);
                        bob.setSilent(true);
                    }
                }
            }
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(m_plugin, new Runnable() {
                public void run()
                {

                    ListIterator<Entity> l_ent_it = l_entities.listIterator();
                    ListIterator<Vector> l_vec_it = l_velocities.listIterator();
                    Entity l_entity;
                    LivingEntity bob;
                    while (l_ent_it.hasNext())
                    {
                        // Bukkit.getServer().getLogger().info("weeeee");
                        l_entity = l_ent_it.next();
                        l_entity.setVelocity(l_vec_it.next());
                        if (!(l_entity instanceof Player))
                        {
                            l_entity.setGravity(true);
                            if (l_entity instanceof LivingEntity)
                            {
                                bob = (LivingEntity) l_entity;
                                bob.setAI(true);
                                bob.setSilent(false);
                            }
                        }
                    }
                }
            }, l_duration);
        }

    }

    private Vector  getReducedVector(Vector p_velocity)
    {
        Vector l_signs = p_velocity.clone();
        Vector l_reduced = p_velocity.clone();  // comment on init à zero, sinon ? D:
        double l_min_value = 0.001;
        double l_min_comp;

        if (l_signs.getX() < 0)
            p_velocity.setX(-p_velocity.getX());
        if (l_signs.getY() < 0)
            p_velocity.setY(-p_velocity.getY());
        if (l_signs.getZ() < 0)
            p_velocity.setZ(-p_velocity.getZ());

        if (p_velocity.getX() < p_velocity.getY() && p_velocity.getX() < p_velocity.getZ())
            l_min_comp = p_velocity.getX();
        else if (p_velocity.getY() < p_velocity.getX() && p_velocity.getY() < p_velocity.getZ())
            l_min_comp = p_velocity.getY();
        else
            l_min_comp = p_velocity.getZ();

        if (l_min_comp == 0)
            l_min_comp = l_min_value;   // NaN sinon :/

        l_reduced.setX(l_min_value * (p_velocity.getX() / l_min_comp));
        l_reduced.setY(l_min_value * (p_velocity.getY() / l_min_comp));
        l_reduced.setZ(l_min_value * (p_velocity.getZ() / l_min_comp));

        if (l_signs.getX() < 0)
            l_reduced.setX(-l_reduced.getX());
        if (l_signs.getY() < 0)
            l_reduced.setY(-l_reduced.getY());
        if (l_signs.getZ() < 0)
            l_reduced.setZ(-l_reduced.getZ());

        return l_reduced;
    }

    private boolean isOnCooldown(String p_playerName, long p_cooldown)
    {
        if (!m_timers.containsKey(p_playerName))
            m_timers.put(p_playerName, 0l);

        if (System.currentTimeMillis() - m_timers.get(p_playerName) < p_cooldown)
            return true;

        return false;
    }

    private void setOnCooldown(String p_playerName)
    {
        m_timers.put(p_playerName, System.currentTimeMillis());
    }

}
