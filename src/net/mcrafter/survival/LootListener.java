package net.mcrafter.survival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class LootListener implements Listener
{
    @EventHandler
    public void onEntityDeath(EntityDeathEvent p_event)
    {
        if (Math.random() % 2 == 0)
            p_event.getDrops().clear();
    }
}
