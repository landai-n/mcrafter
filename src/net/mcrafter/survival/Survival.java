package net.mcrafter.survival;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import net.mcrafter.survival.Creature.CustomCreature;
import net.mcrafter.survival.Creature.CustomSpawner;
import org.bukkit.World;

public class Survival extends EnchantPlugin
{
    private RecipeLoader m_recipeLoader;

    public Survival()
    {
        m_recipeLoader = new RecipeLoader(this);
    }

    @Override
    public void onEnable()
    {
        CustomCreature.registerEntities();
        EnchantmentAPI.registerCustomEnchantment(new AmmoEnchantment());
        EnchantmentAPI.registerCustomEnchantment(new ImmortalEnchantment());
        EnchantmentAPI.registerCustomEnchantment(new ZeusEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new ExplosiveEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new TimeEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FlyEnchantment());

        m_recipeLoader.Load();

        for (World l_world : getServer().getWorlds())
            if (l_world.getEnvironment() != World.Environment.THE_END)
                l_world.setMonsterSpawnLimit(l_world.getMonsterSpawnLimit() * 3);

        getServer().getPluginManager().registerEvents(new CustomSpawner(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new NaturalEventListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterAIListener(), this);
        getServer().getPluginManager().registerEvents(new LootListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
    }

    @Override
    public void onDisable()
    {
        for (World l_world : getServer().getWorlds())
            if (l_world.getEnvironment() != World.Environment.THE_END)
                l_world.setMonsterSpawnLimit(l_world.getMonsterSpawnLimit() / 3);
    }
}
