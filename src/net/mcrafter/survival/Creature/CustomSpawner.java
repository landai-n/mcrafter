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
    public boolean m_spawn = false;
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent p_spawnEvent)
    {
        if (m_spawn)
        {
            m_spawn = false;
            return;
        }
        Class<? extends EntityInsentient> l_customClass = CustomCreature.GetCustomClass(p_spawnEvent.getEntityType());

        if (l_customClass == null)
            return;

        try
        {
            LivingEntity l_baseEntity = p_spawnEvent.getEntity();
            Location l_location = l_baseEntity.getLocation();
            CraftWorld l_craftWorld = (CraftWorld) l_baseEntity.getWorld();
            WorldServer l_world = l_craftWorld.getHandle();
            Entity l_customEntity;

            l_customEntity = l_customClass.getDeclaredConstructor(World.class).newInstance(l_world);


            l_baseEntity.remove();
            l_customEntity.setLocation(l_location.getX(), l_location.getY(), l_location.getZ(), l_location.getYaw(), l_location.getPitch());
            m_spawn = true;
            l_world.addEntity(l_customEntity, CreatureSpawnEvent.SpawnReason.NATURAL);
        }
        catch (Exception exception)
        {
            Bukkit.getLogger().warning(toString() + ": " + exception.toString() + ": " + exception.getMessage());
        }
    }
}
