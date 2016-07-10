package net.mcrafter.survival;

import com.rit.sucy.EnchantmentAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

class PlayerMoveListener implements Listener
{
    private boolean jump = false;
    private static Material[] m_notSolidBlocks = { Material.AIR, Material.LONG_GRASS };

    @EventHandler
    public void OnMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        Block b = p.getWorld().getBlockAt((int)loc.getX(), (int)loc.getY() - 1, (int)loc.getZ());
        if (e.getFrom().getY() < e.getTo().getY() && !IsSolidOrLiquid(b) && !jump)
        {
            OnJumpBegin(e);
            jump = true;
        }
        else if (IsSolidOrLiquid(b) && jump)
        {
            OnJumpEnd(e);
            jump = false;
        }
    }

    private boolean IsSolidOrLiquid(Block p_block)
    {
        for (Material l_material : m_notSolidBlocks)
            if (l_material == p_block.getType())
                return (false);
        return (true);
    }

    private void OnJumpBegin(PlayerMoveEvent p_event)
    {
        Player l_player = p_event.getPlayer();
        ItemStack l_chest = l_player.getInventory().getChestplate();
        Vector l_velocity = l_player.getVelocity();

        if (l_chest != null && l_chest.getType() == Material.ELYTRA && EnchantmentAPI.itemHasEnchantment(l_chest, "Fly"))
        {
            double l_groundVelocity = Math.abs(l_velocity.getX()) + Math.abs(l_velocity.getZ());
            if (l_groundVelocity != 0 && !l_player.isGliding())
            {
                l_player.setGliding(true);
                l_player.setVelocity(l_player.getVelocity().add(
                        new Vector(Max(l_velocity.getX() * 3, 0.8),
                                Max(l_velocity.getY() * 3, 0.8),
                                Max(l_velocity.getZ() * 3, 0.8))));
            }
            else
                System.out.println((l_velocity.getX() + l_velocity.getZ()));
        }

    }

    private double Max(double p_value, double p_max) { return (p_value > p_max ? p_max : p_value); }

    private void OnJumpEnd(PlayerMoveEvent p_event)
    {

    }

}
