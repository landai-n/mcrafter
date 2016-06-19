package net.mcrafter.survival;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

class RecipeLoader
{
    private JavaPlugin m_plugin;

    RecipeLoader(JavaPlugin p_plugin)
    {
        m_plugin = p_plugin;
    }

    void Load()
    {
        SetFurnaceRecipe(Material.LOG, Material.AIR);
        SetFurnaceRecipe(Material.LOG_2, Material.AIR);

        AddImmortalCraft(Material.DIAMOND_SWORD, "Diamond Sword");
        AddImmortalCraft(Material.DIAMOND_AXE, "Diamond Axe");
        AddImmortalCraft(Material.DIAMOND_HOE, "Diamond Hoe");
        AddImmortalCraft(Material.DIAMOND_PICKAXE, "Diamond Pickaxe");
        AddImmortalCraft(Material.DIAMOND_SPADE, "Diamond Shovel");
        AddImmortalCraft(Material.FISHING_ROD, "Fishing Rod");

        AddImmortalCraft(Material.IRON_SWORD, "Iron Sword");
        AddImmortalCraft(Material.IRON_AXE, "Iron Axe");
        AddImmortalCraft(Material.IRON_HOE, "Iron Hoe");
        AddImmortalCraft(Material.IRON_PICKAXE, "Iron Pickaxe");
        AddImmortalCraft(Material.IRON_SPADE, "Iron Shovel");

        AddImmortalCraft(Material.BOW, "Bow");

        AddImmortalCraft(Material.DIAMOND_CHESTPLATE, "Diamond Chestplate");
        AddImmortalCraft(Material.DIAMOND_LEGGINGS, "Diamond Leggings");
        AddImmortalCraft(Material.DIAMOND_HELMET, "Diamond Helmet");
        AddImmortalCraft(Material.DIAMOND_BOOTS, "Diamond Boots");

        AddImmortalCraft(Material.IRON_CHESTPLATE, "Iron Chestplate");
        AddImmortalCraft(Material.IRON_LEGGINGS, "Iron Leggings");
        AddImmortalCraft(Material.IRON_HELMET, "Iron Helmet");
        AddImmortalCraft(Material.IRON_BOOTS, "Iron Boots");

        AddImmortalCraft(Material.CHAINMAIL_CHESTPLATE, "Chain Chestplate");
        AddImmortalCraft(Material.CHAINMAIL_LEGGINGS, "Chain Leggings");
        AddImmortalCraft(Material.CHAINMAIL_HELMET, "Chain Helmet");
        AddImmortalCraft(Material.CHAINMAIL_BOOTS, "Chain Boots");
    }

    private void AddImmortalCraft(Material l_item, String l_name)
    {
        ItemStack l_drop = new ItemStack(l_item);

        l_drop.addUnsafeEnchantment(Survival.immortalEnchantment, 4);
        ItemMeta l_meta = l_drop.getItemMeta();

        l_meta.addEnchant(Survival.immortalEnchantment, 4, true);
        l_meta.setDisplayName("Immortal " + l_name);
        l_drop.setItemMeta(l_meta);

        ShapedRecipe l_recipe = new ShapedRecipe(l_drop);

        l_recipe.shape("xxx", "xox", "xxx");
        l_recipe.setIngredient('x', Material.EMERALD_BLOCK);
        l_recipe.setIngredient('o', l_item);

        m_plugin.getServer().addRecipe(l_recipe);
    }

    private void SetFurnaceRecipe(Material p_input, Material p_output)
    {
        FurnaceRecipe l_recipe = new FurnaceRecipe(new ItemStack(p_output), p_output);

        l_recipe.setInput(p_input);
        m_plugin.getServer().addRecipe(l_recipe);
    }
}
