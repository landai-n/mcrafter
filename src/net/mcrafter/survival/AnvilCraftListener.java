package net.mcrafter.survival;

import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AnvilCraftListener implements Listener
{
    private static Map<Pair<Material[], Material[]>, ItemStack> m_craftList = new HashMap<>();

    static void AddCraft(Material[] p_item1, Material[] p_item2, ItemStack p_result)
    {
        m_craftList.put(new Pair<>(p_item1, p_item2), p_result);
    }

    static void AddCraft(Material p_item1, Material p_item2, ItemStack p_result)
    {
        Material[] l_tab1 = new Material[1];
        Material[] l_tab2 = new Material[1];

        l_tab1[0] = p_item1;
        l_tab2[0] = p_item2;
        m_craftList.put(new Pair<>(l_tab1, l_tab2), p_result);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if(e.getWhoClicked() instanceof Player)
        {
            Player l_player = (Player)e.getWhoClicked();
            InventoryView l_view = e.getView();
            int l_rawSlot = e.getRawSlot();

            if (l_view.getType() == InventoryType.ANVIL)
            {
                if(l_rawSlot != l_view.convertSlot(l_rawSlot))
                    return;
                if (l_rawSlot == 0 || l_rawSlot == 1)
                {
                    l_player.updateInventory();
                    Bukkit.getLogger().info("Update");
                }
                else if (l_rawSlot == 2)
                {
                    AnvilInventory l_anvilInventory = (AnvilInventory) e.getInventory();
                    int slot = e.getRawSlot();

                    if (slot == 2)
                    {
                        ItemStack[] l_anvilContent = l_anvilInventory.getContents();

                        for (Map.Entry<Pair<Material[], Material[]>, ItemStack> l_craft : m_craftList.entrySet()) {
                            for (Material l_item1 : l_craft.getKey().getKey())
                                for (Material l_item2 : l_craft.getKey().getValue()) {
                                    if (l_item1 == l_anvilContent[0].getType() || l_item1 == l_anvilContent[1].getType())
                                        if (l_item2 == l_anvilContent[0].getType() || l_item2 == l_anvilContent[1].getType())
                                        {
                                            Bukkit.getLogger().info("Set item");
                                            ItemStack l_newItem = new ItemStack(l_craft.getValue());
                                            l_anvilInventory.removeItem(l_anvilContent[0], l_anvilContent[1]);

                                            l_player.getWorld().dropItem(l_player.getLocation(), l_newItem);
                                        }
                                }
                        }
                    }
                }
            }
        }
    }
}
