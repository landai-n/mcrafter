package net.mcrafter.survival;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import org.bukkit.World;

public class Survival extends EnchantPlugin
{
    private RecipeLoader m_recipeLoader;
    static ImmortalEnchantment immortalEnchantment = new ImmortalEnchantment();

    public Survival()
    {
        m_recipeLoader = new RecipeLoader(this);
    }

    @Override
    public void onEnable()
    {
        EnchantmentAPI.registerCustomEnchantment(immortalEnchantment);

        m_recipeLoader.Load();

        for (World l_world : getServer().getWorlds())
            l_world.setMonsterSpawnLimit(l_world.getMonsterSpawnLimit() * 3);

        getServer().getPluginManager().registerEvents(new MonsterAIListener(), this);
        getServer().getPluginManager().registerEvents(new LootListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
    }
}
