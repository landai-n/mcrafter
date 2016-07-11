package net.mcrafter.survival;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import net.minecraft.server.v1_10_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent p_event) {
        Bukkit.getLogger().info("Player dead.");
        Player l_player = p_event.getEntity();

        p_event.setKeepInventory(true);
        l_player.setLevel(0);
        l_player.setTotalExperience(0);

        ClearInventory(l_player);
    }

    private void ClearInventory(Player p_player) {
        Stack<ItemStack> l_saved = new Stack<>();

        for (ItemStack l_item : p_player.getInventory())
        {
            if (l_item != null)
            {
                int l_level = EnchantmentAPI.getItemEnchantmentLevel(l_item, "Immortal");

                if (l_level-- > 0)
                {
                    CustomEnchantment l_immortal = EnchantmentAPI.getEnchantment("Immortal");

                    l_immortal.removeFromItem(l_item);
                    if (l_level > 0)
                        l_immortal.addToItem(l_item, l_level);
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
