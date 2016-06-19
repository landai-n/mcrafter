package net.mcrafter.survival;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin
{
    private RecipeLoader m_recipeLoader;
    public static ImmortalEnchantment immortalEnchantment = new ImmortalEnchantment(150);

    public Survival()
    {
        m_recipeLoader = new RecipeLoader(this);
    }

    @Override
    public void onEnable()
    {
        m_recipeLoader.Load();

        for (World l_world : getServer().getWorlds())
            l_world.setMonsterSpawnLimit(l_world.getMonsterSpawnLimit() * 3);

        AnvilCraftListener.AddCraft(Material.GOLD_SWORD, Material.DIAMOND, new ItemStack(Material.DIAMOND_SWORD));
        getServer().getPluginManager().registerEvents(new MonsterAIListener(), this);
        getServer().getPluginManager().registerEvents(new LootListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new AnvilCraftListener(), this);
    }
}
