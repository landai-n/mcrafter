package net.mcrafter.survival;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener
{
    @EventHandler
    public void OnTakeDamage(EntityDamageByEntityEvent p_event)
    {
        if (p_event.getDamager().getType() == EntityType.PIG_ZOMBIE)
            p_event.setDamage(p_event.getDamage() * 0.5);
        if (p_event.getDamager().getType() == EntityType.PLAYER)
            p_event.setDamage(p_event.getDamage() * 1.3);
    }
}
