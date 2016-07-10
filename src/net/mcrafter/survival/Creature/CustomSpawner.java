package net.mcrafter.survival.Creature;

import net.minecraft.server.v1_10_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;

public class CustomSpawner implements Listener
{
    private static boolean m_spawn = false;

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent p_spawnEvent)
    {
        if (m_spawn)
        {
            m_spawn = false;
            return;
        }
        Spawn(p_spawnEvent.getEntityType(), p_spawnEvent.getLocation());
        p_spawnEvent.getEntity().remove();
    }

    public static Entity Spawn(EntityType p_type, Location p_location)
    {
        try
        {
            Class<? extends EntityInsentient> l_customClass = CustomCreature.GetCustomClass(p_type);

            if (l_customClass == null)
                return null;

            CraftWorld l_craftWorld = (CraftWorld) p_location.getWorld();
            WorldServer l_world = l_craftWorld.getHandle();
            Entity l_customEntity;

            l_customEntity = l_customClass.getDeclaredConstructor(World.class).newInstance(l_world);

            l_customEntity.setLocation(p_location.getX(), p_location.getY(), p_location.getZ(), p_location.getYaw(), p_location.getPitch());
            m_spawn = true;
            l_world.addEntity(l_customEntity, CreatureSpawnEvent.SpawnReason.NATURAL);
            return (l_customEntity);
        }
        catch (Exception exception)
        {
            Bukkit.getLogger().warning("CustomSpawner.Spawn" + ": " + exception.toString() + ": " + exception.getMessage());
        }
        return null;
    }
}
