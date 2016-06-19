package net.mcrafter.survival;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class MonsterDeathListener implements Listener
{
    @EventHandler
    void onMonsterDeath(EntityDeathEvent p_monster)
    {
        if (p_monster.getEntityType() == EntityType.ZOMBIE)
            onZombieDeath((Zombie)p_monster.getEntity());
        else if (p_monster.getEntityType() == EntityType.PIG_ZOMBIE)
            onPigZombieDeath((PigZombie) p_monster.getEntity());
    }

    private void onZombieDeath(Zombie p_zombie)
    {
        if (!p_zombie.isBaby())
        {
            int l_random = (int)Math.rint(Math.random()) % 4;

            Bukkit.getLogger().info("Launch zombie minion dice: " + l_random);

            for (int i = 0; i < l_random; ++i)
            {
                Zombie l_baby = (Zombie) p_zombie.getWorld().spawnEntity(p_zombie.getLocation(), EntityType.ZOMBIE);

                l_baby.setBaby(true);
                l_baby.setMaxHealth(l_baby.getMaxHealth() * 0.1);
            }
        }
    }

    private void onPigZombieDeath(PigZombie p_zombie)
    {
        if (!p_zombie.isBaby())
        {
            int l_random = (int)Math.rint(Math.random()) % 4;

            Bukkit.getLogger().info("Launch pig zombie minion dice: " + l_random);

            for (int i = 0; i < l_random; ++i)
            {
                PigZombie l_baby = (PigZombie) p_zombie.getWorld().spawnEntity(p_zombie.getLocation(), EntityType.PIG_ZOMBIE);

                l_baby.setBaby(true);
                l_baby.setMaxHealth(l_baby.getMaxHealth() * 0.1);
            }
        }
    }
}
