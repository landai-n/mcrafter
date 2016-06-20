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

        LoadStuffUncrafts();
    }

    private void LoadStuffUncrafts()
    {
        AddShapelessCraftRecipe(Material.DIAMOND_SWORD, Material.DIAMOND, 1);
        AddShapelessCraftRecipe(Material.DIAMOND_AXE, Material.DIAMOND, 1);
        AddShapelessCraftRecipe(Material.DIAMOND_HOE, Material.DIAMOND, 1);
        AddShapelessCraftRecipe(Material.DIAMOND_PICKAXE, Material.DIAMOND, 1);
        AddShapelessCraftRecipe(Material.DIAMOND_SPADE, Material.DIAMOND, 1);

        AddShapelessCraftRecipe(Material.IRON_SWORD, Material.IRON_INGOT, 1);
        AddShapelessCraftRecipe(Material.IRON_AXE, Material.IRON_INGOT, 1);
        AddShapelessCraftRecipe(Material.IRON_HOE, Material.IRON_INGOT, 1);
        AddShapelessCraftRecipe(Material.IRON_PICKAXE, Material.IRON_INGOT, 1);
        AddShapelessCraftRecipe(Material.IRON_SPADE, Material.IRON_INGOT, 1);

        AddShapelessCraftRecipe(Material.DIAMOND_CHESTPLATE, Material.DIAMOND, 3);
        AddShapelessCraftRecipe(Material.DIAMOND_LEGGINGS, Material.DIAMOND, 2);
        AddShapelessCraftRecipe(Material.DIAMOND_HELMET, Material.DIAMOND, 2);
        AddShapelessCraftRecipe(Material.DIAMOND_BOOTS, Material.DIAMOND, 1);

        AddShapelessCraftRecipe(Material.IRON_CHESTPLATE, Material.IRON_INGOT, 3);
        AddShapelessCraftRecipe(Material.IRON_LEGGINGS, Material.IRON_INGOT, 2);
        AddShapelessCraftRecipe(Material.IRON_HELMET, Material.IRON_INGOT, 2);
        AddShapelessCraftRecipe(Material.IRON_BOOTS, Material.IRON_INGOT, 1);
    }

    private void AddShapelessCraftRecipe(Material p_input, Material p_output, int p_outputNb)
    {
        ShapelessRecipe l_recipe = new ShapelessRecipe(new ItemStack(p_output, p_outputNb));

        l_recipe.addIngredient(p_input);
        m_plugin.getServer().addRecipe(l_recipe);
    }

    private void SetFurnaceRecipe(Material p_input, Material p_output)
    {
        FurnaceRecipe l_recipe = new FurnaceRecipe(new ItemStack(p_output), p_output);

        l_recipe.setInput(p_input);
        m_plugin.getServer().addRecipe(l_recipe);
    }
}
