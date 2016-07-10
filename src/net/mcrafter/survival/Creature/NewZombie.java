package net.mcrafter.survival.Creature;

import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.EntityZombie;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftZombie;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class NewZombie extends EntityZombie
{
    public NewZombie(World p_world)
    {
        super(p_world);
        setCustomName("Michel");
        setCustomNameVisible(true);
    }
}
