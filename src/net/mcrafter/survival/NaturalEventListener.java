package net.mcrafter.survival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class NaturalEventListener implements Listener
{
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event)
    {
        if (event.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING)
            event.setCancelled(true);
    }
}
