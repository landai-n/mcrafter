package net.mcrafter.survival;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsTarget;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftCow;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftMonster;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MonsterAIListener implements Listener
{
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent l_spawnEvent)
    {
        if (!Monster.class.isAssignableFrom(l_spawnEvent.getEntity().getClass()))
            return;
        Monster l_monster = (Monster) l_spawnEvent.getEntity();
        l_monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 4));

        if (l_monster.getType() == EntityType.ZOMBIE)
            TargetNearestPlayer(l_monster, 50);
        else if (l_monster.getType() == EntityType.SPIDER)
            TargetNearestPlayer(l_monster, 50);
        else if (l_monster.getType() == EntityType.SKELETON)
            TargetNearestPlayer(l_monster, 20);
        else if (l_monster.getType() == EntityType.PIG_ZOMBIE)
        {
            PigZombie l_pig = (PigZombie)l_monster;

            l_pig.setAngry(true);
            l_pig.setAnger(1000);
        }
    }

    private void TargetNearestPlayer(Monster p_monster, double p_distanceMax)
    {
        p_monster.setTarget(FindTarget(p_monster, p_distanceMax));
    }

    private Player FindTarget(Monster p_monster, double p_distanceMax)
    {
        Player l_target = FindNearestPlayer(p_monster);

        if (l_target != null && l_target.getLocation().distance(p_monster.getLocation()) < p_distanceMax)
            return (l_target);
        return (null);
    }

    private Player FindNearestPlayer(Monster p_monster)
    {
        Player l_nearestPlayer = null;
        double l_nearestDistance = -1;

        for (Player l_player : Bukkit.getServer().getOnlinePlayers())
        {
            if (l_player.getWorld() != p_monster.getWorld())
                continue;
            double l_distance = l_player.getLocation().distance(p_monster.getLocation());

            if (l_nearestPlayer == null || l_distance < l_nearestDistance)
            {
                l_nearestPlayer = l_player;
                l_nearestDistance = l_distance;
            }
        }
        return (l_nearestPlayer);
    }
}