package net.mcrafter.survival;

import com.rit.sucy.EnchantmentAPI;
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

        AddImmortalCraft(Material.DIAMOND_SWORD);
        AddImmortalCraft(Material.DIAMOND_AXE);
        AddImmortalCraft(Material.DIAMOND_HOE);
        AddImmortalCraft(Material.DIAMOND_PICKAXE);
        AddImmortalCraft(Material.DIAMOND_SPADE);
        AddImmortalCraft(Material.FISHING_ROD);

        AddImmortalCraft(Material.IRON_SWORD);
        AddImmortalCraft(Material.IRON_AXE);
        AddImmortalCraft(Material.IRON_HOE);
        AddImmortalCraft(Material.IRON_PICKAXE);
        AddImmortalCraft(Material.IRON_SPADE);

        AddImmortalCraft(Material.BOW);

        AddImmortalCraft(Material.DIAMOND_CHESTPLATE);
        AddImmortalCraft(Material.DIAMOND_LEGGINGS);
        AddImmortalCraft(Material.DIAMOND_HELMET);
        AddImmortalCraft(Material.DIAMOND_BOOTS);

        AddImmortalCraft(Material.IRON_CHESTPLATE);
        AddImmortalCraft(Material.IRON_LEGGINGS);
        AddImmortalCraft(Material.IRON_HELMET);
        AddImmortalCraft(Material.IRON_BOOTS);

        AddImmortalCraft(Material.CHAINMAIL_CHESTPLATE);
        AddImmortalCraft(Material.CHAINMAIL_LEGGINGS);
        AddImmortalCraft(Material.CHAINMAIL_HELMET);
        AddImmortalCraft(Material.CHAINMAIL_BOOTS);

        AddImmortalCraft(Material.ELYTRA);
    }

    private void AddImmortalCraft(Material l_item)
    {
        ItemStack l_drop = new ItemStack(l_item);

        EnchantmentAPI.getEnchantment("Immortal").addToItem(l_drop, 5);

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
