package fr.dinnerwolph.hub.mount;

import fr.dinnerwolph.hub.customentities.RideableChicken;
import fr.dinnerwolph.hub.customentities.RideableCow;
import fr.dinnerwolph.hub.customentities.RideableCreeper;
import fr.dinnerwolph.hub.customentities.RideableSheep;
import fr.dinnerwolph.hub.customentities.RideableSpider;
import java.util.List;
import net.minecraft.server.v1_9_R2.EntityAgeable;
import net.minecraft.server.v1_9_R2.EntityLiving;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.World;
import net.minecraft.server.v1_9_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MountManager {
    public static double mountSpeed = 0.2;
    public static double maxHealth = 2.0;

    private static void make(EntityLiving nmsEntity, Player player) {
        if (!MountManager.canSummonMount(player.getLocation())) {
            player.sendMessage("Tu ne peux pas faire spawn ta monture ici.");
            return;
        }
        LivingEntity mount = (LivingEntity)nmsEntity.getBukkitEntity();
        if (mount instanceof EntityAgeable) {
            ((EntityAgeable)mount).setAge(0);
        }
        Location loc = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)loc.getWorld()).getHandle();
        nmsEntity.setPosition(loc.getX(), loc.getY() + 0.3, loc.getZ());
        nmsWorld.addEntity((net.minecraft.server.v1_9_R2.Entity)nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        mount.setMaxHealth(maxHealth);
        mount.setPassenger((Entity)player);
        player.closeInventory();
    }

    private static boolean canSummonMount(Location location) {
        org.bukkit.World world = location.getWorld();
        Block block = location.getBlock();
        for (int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; ++x) {
            for (int y = location.getBlockY(); y <= location.getBlockY() + 1; ++y) {
                for (int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; ++z) {
                    block = world.getBlockAt(x, y, z);
                    if (!block.getType().isSolid()) continue;
                    return false;
                }
            }
        }
        return true;
    }

    public static void RideCreeper(Player player) {
        Location location = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)location.getWorld()).getHandle();
        RideableCreeper nmsEntity = new RideableCreeper((World)nmsWorld, player);
        MountManager.make((EntityLiving)nmsEntity, player);
    }

    public static void RideSpider(Player player) {
        Location location = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)location.getWorld()).getHandle();
        RideableSpider nmsEntity = new RideableSpider((World)nmsWorld, player);
        MountManager.make((EntityLiving)nmsEntity, player);
    }

    public static void RideSheep(Player player) {
        Location location = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)location.getWorld()).getHandle();
        RideableSheep nmsEntity = new RideableSheep((World)nmsWorld, player);
        MountManager.make((EntityLiving)nmsEntity, player);
    }

    public static void RideCow(Player player) {
        Location location = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)location.getWorld()).getHandle();
        RideableCow nmsEntity = new RideableCow((World)nmsWorld, player);
        MountManager.make((EntityLiving)nmsEntity, player);
    }

    public static void RideChicken(Player player) {
        Location location = player.getLocation();
        WorldServer nmsWorld = ((CraftWorld)location.getWorld()).getHandle();
        RideableChicken nmsEntity = new RideableChicken((World)nmsWorld, player);
        MountManager.make((EntityLiving)nmsEntity, player);
    }

    public static boolean shouldDie(EntityLiving mount, Player rider) {
        for (net.minecraft.server.v1_9_R2.Entity entity : mount.passengers) {
            if (!(entity instanceof EntityPlayer)) continue;
            return false;
        }
        mount.die();
        return false;
    }
}