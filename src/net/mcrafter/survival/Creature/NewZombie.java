package net.mcrafter.survival.Creature;

import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityZombie;
import net.minecraft.server.v1_10_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_10_R1.World;


public class NewZombie extends EntityZombie
{
    @SuppressWarnings("unchecked")
    public NewZombie(World p_world)
    {
        super(p_world);
        setCustomName("Michel");
        setCustomNameVisible(true);
        this.targetSelector.a(2,
                new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 70, true, true, null));
    }

    public boolean isBaby() { return super.isBaby(); }

    public void setBaby(boolean var1) { super.setBaby(var1); }

    public boolean isVillager() { return super.isVillager(); }

    public void setMaxHealth(double p_health)
    {
        super.setHealth((float)p_health);
    }
}
