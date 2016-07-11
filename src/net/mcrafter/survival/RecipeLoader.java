package net.mcrafter.survival;

import com.rit.sucy.EnchantmentAPI;
import org.bukkit.Bukkit;
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
        LoadImmortalCraft();
        LoadAmmoCraft();
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

        AddShapelessCraftRecipe(Material.GOLD_SWORD, Material.GOLD_INGOT, 1);
        AddShapelessCraftRecipe(Material.GOLD_AXE, Material.GOLD_INGOT, 1);
        AddShapelessCraftRecipe(Material.GOLD_HOE, Material.GOLD_INGOT, 1);
        AddShapelessCraftRecipe(Material.GOLD_PICKAXE, Material.GOLD_INGOT, 1);
        AddShapelessCraftRecipe(Material.GOLD_SPADE, Material.GOLD_INGOT, 1);

        AddShapelessCraftRecipe(Material.DIAMOND_CHESTPLATE, Material.DIAMOND, 3);
        AddShapelessCraftRecipe(Material.DIAMOND_LEGGINGS, Material.DIAMOND, 2);
        AddShapelessCraftRecipe(Material.DIAMOND_HELMET, Material.DIAMOND, 2);
        AddShapelessCraftRecipe(Material.DIAMOND_BOOTS, Material.DIAMOND, 1);

        AddShapelessCraftRecipe(Material.IRON_CHESTPLATE, Material.IRON_INGOT, 3);
        AddShapelessCraftRecipe(Material.IRON_LEGGINGS, Material.IRON_INGOT, 2);
        AddShapelessCraftRecipe(Material.IRON_HELMET, Material.IRON_INGOT, 2);
        AddShapelessCraftRecipe(Material.IRON_BOOTS, Material.IRON_INGOT, 1);

        AddShapelessCraftRecipe(Material.GOLD_CHESTPLATE, Material.GOLD_INGOT, 3);
        AddShapelessCraftRecipe(Material.GOLD_LEGGINGS, Material.GOLD_INGOT, 2);
        AddShapelessCraftRecipe(Material.GOLD_HELMET, Material.GOLD_INGOT, 2);
        AddShapelessCraftRecipe(Material.GOLD_BOOTS, Material.GOLD_INGOT, 1);

        AddShapelessCraftRecipe(Material.LEATHER_CHESTPLATE, Material.LEATHER, 3);
        AddShapelessCraftRecipe(Material.LEATHER_LEGGINGS, Material.LEATHER, 2);
        AddShapelessCraftRecipe(Material.LEATHER_HELMET, Material.LEATHER, 2);
        AddShapelessCraftRecipe(Material.LEATHER_BOOTS, Material.LEATHER, 1);
    }

    void LoadImmortalCraft()
    {
        ItemStack l_item1 = new ItemStack(Material.BOOK);
        ItemStack l_item3 = new ItemStack(Material.BOOK);
        ItemStack l_item5 = new ItemStack(Material.BOOK);

        EnchantmentAPI.getEnchantment("Immortal").addToItem(l_item1, 1);
        EnchantmentAPI.getEnchantment("Immortal").addToItem(l_item3, 3);
        EnchantmentAPI.getEnchantment("Immortal").addToItem(l_item5, 5);

        ShapedRecipe l_immortal1 = new ShapedRecipe(l_item1);
        l_immortal1.shape("eee", "ebe", "eee");
        l_immortal1.setIngredient('e', Material.EMERALD);
        l_immortal1.setIngredient('b', Material.BOOK);

        ShapedRecipe l_immortal3 = new ShapedRecipe(l_item3);
        l_immortal3.shape("ded", "ebe", "ded");
        l_immortal3.setIngredient('e', Material.EMERALD);
        l_immortal3.setIngredient('d', Material.DIAMOND);
        l_immortal3.setIngredient('b', Material.BOOK);

        ShapedRecipe l_immortal5 = new ShapedRecipe(l_item5);
        l_immortal5.shape("ddd", "dbd", "ddd");
        l_immortal5.setIngredient('b', Material.BOOK);
        l_immortal5.setIngredient('d', Material.DIAMOND);

        Bukkit.getServer().addRecipe(l_immortal1);
        Bukkit.getServer().addRecipe(l_immortal3);
        Bukkit.getServer().addRecipe(l_immortal5);
    }

    void LoadAmmoCraft()
    {
        ItemStack l_output = new ItemStack(Material.BOW);

        EnchantmentAPI.getEnchantment("Ammo").addToItem(l_output, 8);
        ShapelessRecipe l_recipe = new ShapelessRecipe(l_output);
        l_recipe.addIngredient(Material.BOW).addIngredient(2, Material.BLAZE_POWDER);
        Bukkit.getServer().addRecipe(l_recipe);
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
