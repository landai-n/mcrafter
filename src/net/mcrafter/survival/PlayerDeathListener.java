package net.mcrafter.survival;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class PlayerDeathListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent p_event)
    {
        Player l_player = p_event.getEntity();

        p_event.setKeepInventory(true);
        l_player.setLevel(0);
        l_player.setTotalExperience(0);
        ClearInventory(l_player);
    }

    private void ClearInventory(Player p_player)
    {
        Stack<ItemStack> l_saved = new Stack<>();

        for (ItemStack l_item : p_player.getInventory())
        {
            if (l_item != null)
            {
                try
                {
                    if (l_item.getEnchantments().containsKey(Survival.immortalEnchantment))
                        l_saved.add(new ItemStack(l_item));
                }
                catch(Exception e)
                {
                    l_saved.add(new ItemStack(l_item));
                }
            }
        }

        p_player.getInventory().clear();
        for (ItemStack l_item : l_saved)
            p_player.getInventory().addItem(l_item);

        System.out.println("Clear inventory and levels from " + p_player.getDisplayName());
    }
}
