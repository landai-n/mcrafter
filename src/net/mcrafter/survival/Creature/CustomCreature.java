package net.mcrafter.survival.Creature;

import com.google.common.collect.Maps;
import net.minecraft.server.v1_10_R1.Entity;
import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.EntityTypes;
import net.minecraft.server.v1_10_R1.EntityZombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public enum CustomCreature
    {
        ZOMBIE("Zombie", 54, EntityType.ZOMBIE, EntityZombie.class, NewZombie.class);

        private String name;
        private int id;
        private EntityType entityType;
        private Class<? extends EntityInsentient> originClass;
        private Class<? extends EntityInsentient> customClass;

        CustomCreature(String p_name, int p_id, EntityType p_entityType, Class<? extends EntityInsentient> p_originClass,
                 Class<? extends EntityInsentient> p_customClass)
        {
            this.name = p_name;
            this.id = p_id;
            this.entityType = p_entityType;
            this.originClass = p_originClass;
            this.customClass = p_customClass;
        }

        public String getName(){
            return this.name;
        }

        public int getID(){
            return this.id;
        }

        public EntityType getEntityType(){
            return this.entityType;
        }

        public Class<? extends EntityInsentient> getNMSClass(){
            return this.originClass;
        }

        public Class<? extends EntityInsentient> getCustomClass(){
            return this.customClass;
        }

        public static void registerEntities()
        {
            for (CustomCreature entity : values())
            {
                try
                {
                    if (!UnregisterEntity(entity))
                    {
                        System.out.println("Unable to register custom entity " + entity.getName());
                        continue;
                    }
                    Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[]{Class.class, String.class, int.class});
                    a.setAccessible(true);
                    a.invoke(null, entity.getCustomClass(), entity.getName(), entity.getID());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        @SuppressWarnings("unchecked")
        private static boolean UnregisterEntity(CustomCreature p_entity)
        {
            Map<String, Class<? extends net.minecraft.server.v1_10_R1.Entity>> c;
            Map<Class<? extends net.minecraft.server.v1_10_R1.Entity>, String> d;
            Map<Integer, Class<? extends net.minecraft.server.v1_10_R1.Entity>> e;
            Map<Class<? extends net.minecraft.server.v1_10_R1.Entity>, Integer> f;
            Map<String, Integer> g;

            try
            {
                Field modifiers;

                Field cfield = EntityTypes.class.getDeclaredField("c");
                cfield.setAccessible(true);
                modifiers = cfield.getClass().getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(cfield, cfield.getModifiers() & ~Modifier.FINAL);
                c = (Map<String, Class<? extends net.minecraft.server.v1_10_R1.Entity>>)cfield.get(cfield);
                c.remove(p_entity.getName());
                cfield.set(null, c);

                Field dfield = EntityTypes.class.getDeclaredField("d");
                dfield.setAccessible(true);
                modifiers = dfield.getClass().getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(dfield, dfield.getModifiers() & ~Modifier.FINAL);
                d = (Map<Class<? extends net.minecraft.server.v1_10_R1.Entity>, String>)dfield.get(dfield);
                d.remove(p_entity.getNMSClass());
                dfield.set(null, d);

                Field efield = EntityTypes.class.getDeclaredField("e");
                efield.setAccessible(true);
                modifiers = efield.getClass().getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(efield, efield.getModifiers() & ~Modifier.FINAL);
                e = (Map<Integer, Class<? extends net.minecraft.server.v1_10_R1.Entity>>)efield.get(efield);
                e.remove(p_entity.getID());
                efield.set(null, e);

                Field ffield = EntityTypes.class.getDeclaredField("f");
                ffield.setAccessible(true);
                modifiers = ffield.getClass().getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(ffield, ffield.getModifiers() & ~Modifier.FINAL);
                f = (Map<Class<? extends net.minecraft.server.v1_10_R1.Entity>, Integer>)ffield.get(ffield);
                f.remove(p_entity.getNMSClass());
                ffield.set(null, f);

                Field gfield = EntityTypes.class.getDeclaredField("g");
                gfield.setAccessible(true);
                modifiers = gfield.getClass().getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(gfield, gfield.getModifiers() & ~Modifier.FINAL);
                g = (Map<String, Integer>)gfield.get(gfield);
                g.remove(p_entity.getName());
                gfield.set(null, g);
            }
            catch (NoSuchFieldException | IllegalAccessException exception)
            {
                System.out.println(exception.getClass().toString() + ": " + exception.getMessage());
                return (false);
            }
            return (true);
        }
    }

